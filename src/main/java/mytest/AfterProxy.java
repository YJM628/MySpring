package mytest;

import aop.AbstractProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AfterProxy extends AbstractProxy {
    @Override
    public void after(Class cls, Method method, Object[] methodParms,Object result) {
        System.out.println("After后置增强>>>>");
    }
}
