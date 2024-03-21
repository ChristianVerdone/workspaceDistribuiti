package it.unisannio.netEmulator;

public  class Node {
	  protected int thisId; 	// Id of the node
	  protected Node[] network; // List of the the known nodes
	  protected Info value;		// Storage 
	  
	  private Packet buffer;	// Buffer for communication
	  private boolean available = false;	// buffer state
	  protected Receiver receiver;	// Receiving activity
	  protected Sender sender;		// Sending activity
	  
	  
	  protected Node(Info val, Node[] net, int i) {
		  value = val;
		  network = net;
		  thisId = i;
		  buffer = new Packet(val, i);
		  ++Network.size;
	  }
	  protected Node(Info val, Node[] net, int i, Sender s, Receiver r) {
		  this(val, net, i);
		  sender = s;
		  receiver = r;
	  }
	  
	  // Start the node activities
	  public void start() {
		  if (receiver!= null) receiver.start();
		  if (sender!= null) sender.start();
	  }
	  // Interrupt the node activities
	  public void interrupt() {
		  if (receiver!= null) receiver.interrupt();
		  if (sender!= null) sender.interrupt();
	  }
	  
	  // Read an info from the storage system of the node
	  public Info getValue() {
		  return value;
	  }
	  // Write an info to the storage system of the node
	  private void setValue(Info c) {
		  value = c;
	  }
	  
	  // Write a packet p to the node buffer
	  public synchronized void setPacket(Packet p) throws InterruptedException { 
		  while (available) wait();
		  buffer = p;
		  available = true;
		  notifyAll();
	  }
	  // Read a packet from the node buffer. Blocking semantics
	  private synchronized Packet getPacket() throws InterruptedException {
		  while(!available) wait(); 
		  available = false;
		  notifyAll();
		  return buffer;
	  }
	  // Send a packet p to a peer 
	  protected void send(Packet p, int peer) throws InterruptedException {
		  network[peer].setPacket(p);
	  }
	 
	  // Reply a packet p to a peer to store the content directly on the node
	  protected void reply(Packet p, int peer) {
		  network[peer].setValue(p.content);
	  }
	  // Receive a packet from the current node
	  protected Packet receive() throws InterruptedException {		
		 return getPacket();
	  }
	  
	  // Abstract class for a receiving activity 
	  protected abstract class Receiver extends Thread {
		  protected Receiver() {
			  super(Network.tg, "netReceiver");
		  }  
	  }
	 
	  // Abstract class for a sending activity
	  protected abstract class Sender extends Thread {
		  protected Sender() {
			  super(Network.tg, "netSender");
		  }
	  }
	  
}
