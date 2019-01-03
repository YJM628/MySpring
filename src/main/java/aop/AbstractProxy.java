package aop;

import java.lang.reflect.Method;

//代理模板类
public class AbstractProxy implements Proxy {
    @Override
    public Object doProxy(ProxyChain proxyChain) {
        Object result=null;
        Class<?> targetClass = proxyChain.getTargetClass();
        Method method = proxyChain.getMethod();
        Object[] methodParams = proxyChain.getMethodParams();
        begin();
        try {
            if(filter(targetClass,method,methodParams)){
                before(targetClass,method,methodParams);
                result=proxyChain.doProxyChain();
                after(targetClass,method,methodParams,result);
            }else {
                result=proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            error(targetClass,method,methodParams,e);
        }finally {
            end();
        }
        return  result;
    }
    //交由子类实现
    public  void begin(){

    }
    public boolean filter(Class cls,Method method,Object[] methodParms){
        return  true;
    }
    public  void before(Class cls,Method method,Object[] methodParms){

    }
    public  void after(Class cls,Method method,Object[] methodParms,Object result){

    }
    public  void error(Class cls,Method method,Object[] methodParms,Throwable e){

    }
    public  void end(){

    }
}
