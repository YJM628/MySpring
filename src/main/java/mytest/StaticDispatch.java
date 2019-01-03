package mytest;

public class StaticDispatch {
    static  abstract  class Human{}

    static  class Man extends  Human{}

    static  class  Woman extends  Human{}

    public  void sayHello(Human human){
        System.out.println("hello,human");
    }
    public  void sayHello(Man man){
        System.out.println("hello,man");
    }
    public  void sayHello(Woman woman){
        System.out.println("hello,woman");
    }

    public static void main(String[] args) {
        Human man=new Man();
        Human woman=new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello((Man)man);
        staticDispatch.sayHello((Woman)woman);
    }
}
