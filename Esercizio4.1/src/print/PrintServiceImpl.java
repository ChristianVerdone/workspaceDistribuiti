package print;

import javax.jws.WebService;

@WebService(targetNamespace = "http://print/", endpointInterface = "print.PrintService", portName = "PrintServiceImplPort", serviceName = "PrintServiceImplService")
public class PrintServiceImpl implements PrintService {

	@Override
	public void print(String s) {
		// TODO Auto-generated method stub
		System.out.println(s);
	}
	
}
