package mytest;

public class OutServiceImpl implements  IOutservice{
    public void selfOut(String text) {
        System.out.println("自定义输出>>>"+text);
    }
}
