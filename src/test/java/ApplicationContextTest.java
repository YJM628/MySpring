import context.ClassPathXmlApplicationContext;
import mytest.IHelloService;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;

public class ApplicationContextTest {
    @Test
    public  void test() throws  Exception{
        Object o = new Object();
        HashSet<String> set=new HashSet<String>();
        set.add("a");


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mySpring.xml");
        IHelloService helloService = (IHelloService) context.getBean("helloService");
        helloService.sayHello();
        //Proxy.newProxyInstance()
        byte[] $PrxoyYYS = ProxyGenerator.generateProxyClass("$PrxoyYY", new Class[]{IHelloService.class});
        FileOutputStream fileOutputStream = new FileOutputStream(new File("G:\\$PrxoyYY.class"));
        fileOutputStream.write($PrxoyYYS);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
