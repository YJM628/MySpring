package mytest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TestSwap {
    public static void main(String[] args) {
//        Integer a =1;
//        Integer b =2;
//        System.out.println("a=="+a+",b=="+b);
//        swap(a,b);
//        System.out.println("after,swap(),a=="+a+",b=="+b);
        Map<String,String> map=new HashMap<String, String>();
        map.put("张三","你好");
        String put = map.put("张三", "你也好");
        String value = map.get("张三");
        System.out.println(put);
    }
    public static void swap(Integer n1,Integer n2){
//        Integer temp=n1;
//        n1=n2;
//        n2=temp;
        try {
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        int temp = n1.intValue();
        field.set(n1,n2);
        field.set(n2,new Integer(temp));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
