import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyBaseTest {
    private  String baseName="base";

    public MyBaseTest() {
        callName();
    }

    public void callName(){
        System.out.println(baseName);
    }
    static  class SubBase extends  MyBaseTest{
        private  String baseName="sub";

        @Override
        public void callName() {
            System.out.println(baseName);
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(myThread);
    }
}
