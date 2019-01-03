import aop.ProxyManager;
import mytest.AfterProxy;
import mytest.BeforeProxy;
import mytest.Person;
import mytest.PersonProxy;
import net.sf.cglib.proxy.Proxy;

import java.util.ArrayList;
import java.util.List;

public class MyCglibTest {
    public static void main(String[] args) {
        List<aop.Proxy> proxyList=new ArrayList<aop.Proxy>();
        proxyList.add(new BeforeProxy());
        proxyList.add(new AfterProxy());

        ProxyManager proxyManager = new ProxyManager(Person.class,proxyList);
        Person prxoy = proxyManager.createPrxoy();
        prxoy.say();

    }
}
