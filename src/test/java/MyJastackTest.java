import com.google.common.collect.HashMultiset;

import java.io.IOException;
import java.io.InputStream;

public class MyJastackTest {
    public static void main(String[] args) {
        InputStream in = System.in;
        try {
            int read = in.read();
            System.out.println("exit.....");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMultiset<Integer> objects = HashMultiset.create();

    }
    static  class  TestTask implements  Runnable{
        private  Object obj1;
        private  Object obj2;
        private  int orderId;

        public TestTask(Object obj1, Object obj2, int orderId) {
            this.obj1 = obj1;
            this.obj2 = obj2;
            this.orderId = orderId;
        }

        @Override
        public void run() {
            while(true){
                if(orderId==1){
                    this.test1();
                }else {
                    this.test2();
                }
            }
        }
        public void test1(){
            synchronized (obj1){
                Thread.yield();
                synchronized (obj2){
                    System.out.println("执行test1.....");
                }
            }
        }
        public  void test2(){
            synchronized (obj2){
                Thread.yield();
                synchronized (obj1){
                    System.out.println("执行test2.......");
                }
            }
        }
    }
}
