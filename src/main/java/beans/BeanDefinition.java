package beans;
//bean的包装类
public class BeanDefinition {
    private  Object bean;
    private  Class beanClass;
    private  String className;
    private  InjectObjectList injectObjectList=new InjectObjectList();
    public BeanDefinition() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        try {
            this.beanClass  = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public InjectObjectList getInjectObjectList() {
        return injectObjectList;
    }

    public void setInjectObjectList(InjectObjectList injectObjectList) {
        this.injectObjectList = injectObjectList;
    }
}
