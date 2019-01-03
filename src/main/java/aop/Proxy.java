package aop;
//自定义的代理接口
public interface Proxy {
    Object  doProxy(ProxyChain proxyChain);
}
