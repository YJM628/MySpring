package aop;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ProxyChain {
    private List<Proxy> proxyList;
    private  int currentProxyIndex=0;

    private Class<?> targetClass;
    private Object targetObject;
    private Method method;
    private Object[] methodParams;
    private MethodProxy methodProxy;
    private Object methodResult;

    public ProxyChain() {
    }

    public ProxyChain(Builder builder) {
        this.proxyList = builder.proxyList;
        this.targetClass = builder.targetClass;
        this.targetObject = builder.targetObject;
        this.method = builder.method;
        this.methodParams = builder.methodParams;
        this.methodProxy = builder.methodProxy;
    }
    static  class  Builder{
        private List<Proxy> proxyList;
        private Class<?> targetClass;
        private Object targetObject;
        private Method method;
        private Object[] methodParams;
        private MethodProxy methodProxy;

        public Builder setProxyList(List<Proxy> proxyList) {
            this.proxyList = proxyList;
            return this;
        }

        public Builder setTargetClass(Class<?> targetClass) {
            this.targetClass = targetClass;
            return  this;
        }

        public Builder setTargetObject(Object targetObject) {
            this.targetObject = targetObject;
            return  this;
        }

        public Builder setMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder setMethodParams(Object[] methodParams) {
            this.methodParams = methodParams;
            return  this;
        }

        public Builder setMethodProxy(MethodProxy methodProxy) {
            this.methodProxy = methodProxy;
            return  this;
        }

        public ProxyChain build(){
           return  new ProxyChain(this);
        }
    }
    public List<Proxy> getProxyList() {
        return proxyList;
    }

    public int getCurrentProxyIndex() {
        return currentProxyIndex;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Object getMethodResult() {
        return methodResult;
    }

    public Object  doProxyChain(){
        try {
            if(currentProxyIndex<proxyList.size()){
                 methodResult=proxyList.get(currentProxyIndex++).doProxy(this);
            }else {
                 methodResult=methodProxy.invokeSuper(targetObject, methodParams);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return  methodResult;
    }
}
