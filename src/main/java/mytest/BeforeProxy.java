package mytest;

import aop.AbstractProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class BeforeProxy extends AbstractProxy {
    @Override
    public void before(Class cls, Method method, Object[] methodParms) {
        System.out.println("Before前置增强>>>>>");
    }
}
