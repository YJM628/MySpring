import mytest.IHelloService;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMyProxy {
    @Test
    public void buildProxyClass() throws IOException {
        byte[] userServiceImpls = ProxyGenerator.generateProxyClass("UserServiceImpl", new Class[]{IHelloService.class});
        String fileName = System.getProperty("user.dir") + "/target/userServiceImpl$Proxy.class";
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(userServiceImpls);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
