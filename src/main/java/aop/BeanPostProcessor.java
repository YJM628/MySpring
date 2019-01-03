package aop;
//可以看作bean的初始化aop拦截器
public interface BeanPostProcessor {
    //前置拦截
    Object postProcessBeforeInitialization(Object bean,String beanName) throws  Exception;
    //后置拦截
    Object postProcessorAfterInitialization(Object bean,String beanName) throws  Exception;
}
