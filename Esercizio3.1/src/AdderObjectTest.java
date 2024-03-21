import java.util.Scanner;

public class AdderObjectTest {
    public static void main(String[] args) {
        AdderFactory af= new AdderActiveObjectFactory();


        Adder a = ((AdderActiveObjectFactory) af).create();


        System.out.println("AdderObjectTest.main");

        Scanner s = new Scanner(System.in);
        s.nextLine();
        System.out.println("AdderObjectTest.main");



        long startTime=System.currentTimeMillis();
        for(int i=0; i<1000; i++){
            a.add(2*i,3*i);

        }
        long elapsed =System.currentTimeMillis() -startTime;
        System.out.println("All calls finished: " + elapsed + " ms elapsed");
       ActiveObject.schedulers.interrupt();
       s.close();
    }
}
