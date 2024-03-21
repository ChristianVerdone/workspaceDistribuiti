import org.apache.cxf.jaxrs.client.WebClient;

import it.unisannio.math.Product;

public class Client {

	public static void main(String[] args) {
		WebClient client = WebClient.create("http://127.0.0.1:6000/Esercizio5.2/rest/app2");
		client.accept("application/json").path("/products").query("a", 15F).query("b", 3F);

//			String b = client.get(String.class);
//			System.out.println(b);
		Product b = client.get(Product.class);
		System.out.println(b.getValue());
	}
}
