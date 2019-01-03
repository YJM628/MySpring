package plugins;

import aop.BeanPostProcessor;

public class MyInterceptor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println(beanName+">>>>bean初始化前执行的操作>>>");
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println(beanName+">>>>bean初始化后执行的操作>>>");
        return bean;
    }
}
