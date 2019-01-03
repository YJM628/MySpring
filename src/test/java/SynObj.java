public class SynObj {
    private  static  final  String aa="final修饰的aa";

    public  void add(String name){
        System.out.println("线程"+name+"执行add方法");
        System.out.println(name+"的aa>>"+aa);

    }

    public synchronized  void update(String name){
        System.out.println("线程"+name+"执行update方法");
    }
}
