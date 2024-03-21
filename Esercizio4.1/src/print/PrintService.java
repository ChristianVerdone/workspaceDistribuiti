package print;

import javax.jws.WebService;

@WebService(name = "PrintService", targetNamespace = "http://print/")
public interface PrintService {

	public void print(String s);
}
