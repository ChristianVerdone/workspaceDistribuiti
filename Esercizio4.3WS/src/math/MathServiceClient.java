
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package math;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 3.3.1
 * 2019-03-28T16:52:44.181+01:00
 * Generated source version: 3.3.1
 *
 */
public class MathServiceClient {

    public static void main(String[] args) throws Exception {
        QName serviceName = new QName("http://math/", "MathServiceImplService");
        QName portName = new QName("http://math/", "MathServiceImplPort");

        Service service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING,
                        "http://localhost:6000/Esercizio4.3WS/services/MathServiceImplPort");
        math.MathService client = service.getPort(portName,  math.MathService.class);

        // Insert code to invoke methods on the client here
        System.out.println("Multiplication 3*4 = "+client.multiply(3, 4));
		System.out.println("Division 3/4 = "+client.divide(3, 4));
		System.out.println("Factorial of 4 = "+client.factOf(4));
    }

}