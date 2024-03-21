package it.unisannio.chat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/chat")

public class ChatServer {
	
	private static final String GUEST_PREFIX = "Guest";
	private static int connectionIds;
	private static Set<ChatServer> connections = new CopyOnWriteArraySet<>();
	
	private final String nickname;
	private Session session;
	
	public ChatServer() {
		nickname = GUEST_PREFIX + connectionIds++;
	}
	
	@OnOpen
	public void start(Session session) {
		this.session = session;
		connections.add(this);
		String message = "* "+nickname+ " Si è collegato alla chat.";
		broadcast(message);
	}
	
	@OnClose
	public void end() {
		connections.remove(this);
		String message = "* "+nickname+ " Si è disconnesso.";
		broadcast(message);
	}
	
	
	@OnMessage
	public void receive(String message) {
		String myMessage = "-> "+nickname+ ": "+message;
		broadcast(myMessage);
		//TODO
	}
	
	//TODO @...
	public void onError(Throwable t) throws Throwable{
		
	}

	
private void broadcast(String msg) {
	for(ChatServer client : connections) {
		try {
			synchronized (client) {
				client.session.getBasicRemote().sendText(msg);
			}
		}catch(IOException e) {
			connections.remove(client);
			try {
				client.session.close();
			}catch(IOException e2) {
				e2.printStackTrace();
			}
			String message = "* "+nickname+ " Si è disconnesso.";
			broadcast(message);
		}
	}
}
}
