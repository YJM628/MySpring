package aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class ProxyManager {

    private  Class<?> targetClass;
    private List<Proxy> proxyList;

    public ProxyManager(Class<?> targetClass, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.proxyList = proxyList;
    }

    public <T> T createPrxoy(){
        return (T) Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                ProxyChain proxyChain=new ProxyChain.Builder().setTargetObject(o).setMethod(method).setMethodParams(objects).setMethodProxy(methodProxy).setProxyList(proxyList).build();
                proxyChain.doProxyChain();
                return proxyChain.getMethodResult();
            }
        });
    }
}
