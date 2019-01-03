package aop;

import beans.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

//可以看作是模板模式  实现BeanFactory里面的方法  然后提供公共方法给子类调用
//同时 把依赖注入的实现 留到 子类  由子类 来实现
public abstract  class AbstractBeanFactory implements BeanFactory {
    private Map<String,BeanDefinition> beanMap=new HashMap<String, BeanDefinition>();
    private  final  List<String> beanNameList=new ArrayList<String>();//插件的名字
    private  List<BeanPostProcessor> beanPostProcessorList=new ArrayList<BeanPostProcessor>();
    //实现父类的获取bean方法
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanMap.get(name);
        if(null == beanDefinition){
            throw new IllegalArgumentException("no bean named "+name+"is defined");
        }
        //生成bean  最简单的方式就是运用反射class.forName(name)
        Object bean = beanDefinition.getBean();
        if(null == bean){
            //1.通过Class.newInstance 实例化bean对象
            //2.对对象进行依赖注入
            //3.遍历拦截器  调用拦截器的对应方法  两个for循环
            //备注:在读取xml文件的时候setClassName的时候，顺便通过Class.forName(className),实例化了Class类,
            // 所以这里是能通过getBeanClass获取到Class类信息的
            bean = beanDefinition.getBeanClass().newInstance();
            beanDefinition.setBean(bean);
            //此时需要beanDeinition里的依赖进行注入
            List<InjectObject> injectObjectList = beanDefinition.getInjectObjectList().getInjectObjectList();
            for (InjectObject injectObject : injectObjectList) {
                Object value = injectObject.getValue();
                if(value instanceof  BeanRef){
                    BeanRef beanRef= (BeanRef) value;
                    value=getBean(beanRef.getName());
                }
                //使用set方法进行注入
                System.out.println("injectObject.getName()>>>>"+injectObject.getName());
                try {
                    Method method = bean.getClass().getDeclaredMethod("set"+injectObject.getName().substring(0, 1).toUpperCase()+injectObject.getName().substring(1), value.getClass());
                    method.setAccessible(true);
                    method.invoke(bean,value);
                } catch (NoSuchMethodException e) {
                    Field declaredField = bean.getClass().getDeclaredField(injectObject.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(bean,value);
                    System.out.println("依赖注入失败>>>"+e.getMessage());
                }finally {
                    invokeBeanPostProcessorMethod(name, bean);
                    beanDefinition.setBean(bean);
                }
            }

        }
        return bean;
    }

    private void invokeBeanPostProcessorMethod(String name, Object bean) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            beanPostProcessor.postProcessBeforeInitialization(bean,name);

        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            beanPostProcessor.postProcessorAfterInitialization(bean,name);
        }
    }

    //注册bean  用一个map存储bean对象
    public  void registerBean(String name,BeanDefinition beanDefinition){
         beanMap.put(name,beanDefinition);
    }

    public List  getPluginsByType(Class type) throws  Exception{
         List list=new ArrayList<Object>();
        for (String pluginName : beanNameList) {
            //isAssignableFrom   备注  type为beanClass的接口    或者 type为beanClass
            Object bean1 = beanMap.get(pluginName).getBean();
            if(bean1 instanceof  BeanPostProcessor){
                System.out.println("pluginName>>>前"+pluginName);
                Object bean = getBean(pluginName);
                System.out.println("pluginName>>>后"+pluginName);
                list.add(bean);
            }
//            if(type.isAssignableFrom(beanMap.get(pluginName).getBeanClass())){
//
//            }
        }
        System.out.println("添加插件成功"+list.size());
        return  list;
    }
    public  void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessorList.add(beanPostProcessor);
    }
    public  void initializationSingleTon() throws  Exception{
        for(Iterator<String> it = this.beanNameList.iterator();it.hasNext();){
            String beanName = it.next();
            getBean(beanName);
        }
    }
}
