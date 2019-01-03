package context;

import aop.AbstractBeanFactory;
import aop.BeanPostProcessor;
import beans.BeanFactory;

import java.util.List;

//抽象模板类
public abstract  class AbstractApplicationContext implements  ApplicationContext{
    //需要持有一个AbstractBeanFactory的引用   然后在实例化AbstractApplicationContext子类的时候
    // 同时通过AbstractApplicationContext的构造函数实例化abstractBeanFactory
    protected AbstractBeanFactory abstractBeanFactory;
    public AbstractApplicationContext(AbstractBeanFactory abstractBeanFactory) {
        this.abstractBeanFactory = abstractBeanFactory;
    }

    //先预留一个加载bean对象的方法  由子类实现  可能为xml加载  或者普通的txt文件加载   由不同子类去实现
    public  abstract void loadBean(AbstractBeanFactory beanFactory) throws  Exception;
    public Object getBean(String name) throws Exception {
        return abstractBeanFactory.getBean(name);
    }
    //子类调用
    public  void refresh() throws  Exception{
       //1.先实例化bean对象的Class对象   bean暂时不进行实例化
        // 当需要对bean进行实例化时  再调用Class.newInstance()进行实例化   因为可能会进行懒加载  所以不用一来就进行实例化
        //2.添加BeanPostProcessor拦截器
        loadBean(abstractBeanFactory);//此时会调用子类的具体加载bean的方式
        addPlugins(abstractBeanFactory);//初始化插件  也即初始化BeanPostProcessor
        onRefresh(abstractBeanFactory);
    }
    //
    public void addPlugins(AbstractBeanFactory beanFactory) throws  Exception{
        List beanPostProcessorList = beanFactory.getPluginsByType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessorList) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }
    //此时再通过beanClass.newInstance来进行实例化   同时也会添加 拦截器到对应的bean的前后
    //所以这步主要用于Aop
    public  void onRefresh(AbstractBeanFactory beanFactory) throws  Exception{
        //调用beanFactory的初始化单例对象的方法
        beanFactory.initializationSingleTon();
    }
}

