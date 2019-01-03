package context;

import aop.AbstractBeanFactory;
import aop.AutowireCapableBeanFactory;
import beans.BeanDefinition;
import beans.BeanRef;
import beans.InjectObject;
import io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//采用xml的方式来读取配置文件  并初始化bean对象
public class ClassPathXmlApplicationContext extends  AbstractApplicationContext{
    private Map<String,BeanDefinition> registerMap=new HashMap<String, BeanDefinition>();
    private  String configLocation;//配置文件路径
    //实例化时 通过此构造函数传入文件位置
    public ClassPathXmlApplicationContext(String configLocation) throws  Exception{
        this(new AutowireCapableBeanFactory(),configLocation);
    }
    public ClassPathXmlApplicationContext(AbstractBeanFactory abstractBeanFactory, String configLocation)  throws  Exception{
        super(abstractBeanFactory);
        this.configLocation = configLocation;
        refresh();
    }


    public void loadBean(AbstractBeanFactory beanFactory) throws  Exception{
         //此时 读取并解析本地xml文件
        ResourceLoader resourceLoader = new ResourceLoader(configLocation);
        InputStream inputStream = resourceLoader.getInputStream();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if(node instanceof  Element){
               Element element=(Element) node;
                String name = element.getAttribute("id");
                String className = element.getAttribute("class");
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setClassName(className);//备注:这里的setClassName会通过Class.forName(name)进行反射调用

                NodeList property = element.getElementsByTagName("property");
                for (int j = 0; j < property.getLength(); j++) {
                    Node propertyNode = property.item(j);
                    if(propertyNode instanceof  Element){
                        Element propertyEle= (Element) propertyNode;
                        String attribute = propertyEle.getAttribute("name");
                        String value = propertyEle.getAttribute("value");
                        if(value!=null && value.length()>0){
                              beanDefinition.getInjectObjectList().addInjectObject(new InjectObject(attribute,value));
                        }else {
                            String ref = propertyEle.getAttribute("ref");
                            if(ref ==null || ref.length()==0){
                                 throw new IllegalArgumentException("ref is null");
                            }
                            BeanRef beanRef = new BeanRef(ref);
                            beanDefinition.getInjectObjectList().addInjectObject(new InjectObject(attribute,beanRef));
                            //注意此时 并没有实例化  只是把对应的name以及value放到beanDefinition 然后注册到registerMap
                            //最终还是交给beanFactory或者beanFactory的实现类来进行实例化
                        }
                    }
                }
                registerMap.put(name,beanDefinition);
            }
        }
        //记得 关闭流
        inputStream.close();
        //将beanDefinition注册到beanFactory
        Set<String> beanNames = registerMap.keySet();
        for (String name : beanNames) {
            //注册到beanFactory的beanMap
            BeanDefinition beanDefinition = registerMap.get(name);
            beanFactory.registerBean(name,beanDefinition);
        }
    }
}
