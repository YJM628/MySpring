package mytest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonProxy implements MethodInterceptor {
    private  Object targetObj;
    public  Object getProxy(Object obj){
        this.targetObj=obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObj.getClass());
        enhancer.setCallback(this);
        return  enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理类执行前......");
        methodProxy.invokeSuper(o,objects);
        System.out.println("代理类执行后......");
        return null;
    }
}
