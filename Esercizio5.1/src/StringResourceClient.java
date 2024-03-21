
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class StringResourceClient {
	public static void main(String[] args) {
		WebClient client = WebClient.create("http://localhost:6000/Esercizio5.1/rest/app1");

		// POST /strings
		// client.reset();
		client.accept("text/plain").type("text/plain");
		client.path("/strings");
		Response res = client.post("Hello JAX-RS");
		System.out.println("l'id della risorsa creata è " + res.getLocation());
		System.out.println();

		// GET /strings/id
		client.reset();
		client.accept("text/plain").type("text/plain");
		client.path("/strings/1");
		Response res1 = client.get();
		System.out.println("la stringa recuperata è " + res1.readEntity(String.class));

	}
}
