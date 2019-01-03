package mytest;

public class HelloServiceImpl implements  IHelloService{
    private  String text;
    private  IOutservice outService;
    public void sayHello() {
        outService.selfOut(text);
    }
    public void setText(String text) {
        this.text = text;
    }

    public void setOutService(IOutservice outService) {
        this.outService = outService;
    }
}
