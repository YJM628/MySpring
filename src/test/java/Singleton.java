public class Singleton {
    //1.分配内存空间
    //2.初始化对象
    //3.将内存空间  地址  赋给对应的对象
    //但 2,3  可能会发生重排序


    //解决方案
    //1.使用volatile 保证可见性  禁止重排序
    //2.结合 java的类加载机制  JVM(使用classloder进行初始化时)在类初始化阶段会获取一个锁，这个锁可以同步多个线程对同一个类的初始化。
    //保证2,3的重排序对其余线程不可见  相当于 单线程时重排序不会影响 最终结果
    private  static  Singleton singleton=null;
    public  Singleton getInstance(){

        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;
    }
}
