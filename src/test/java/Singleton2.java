public class Singleton2 {
    //静态内部类
    private  static  class SingletonHolder{
        public static Singleton2 singleton2=new Singleton2();
    }
    private  static Singleton2 getInstance(){
        return  SingletonHolder.singleton2;
    }
}
