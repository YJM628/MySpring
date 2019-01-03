import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySynchroinedTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynObj synObj = new SynObj();
        ProducterAdd producterAdd = new ProducterAdd(synObj);
        executorService.submit(producterAdd);

        SynObj synObj1 = new SynObj();

        ProducterUptate producterUptate = new ProducterUptate(synObj1);
        executorService.submit(producterUptate);
    }
     static  class ProducterAdd implements  Runnable{
         private SynObj obj;

         public ProducterAdd(SynObj obj) {
             this.obj = obj;
         }

         @Override
         public void run() {
             Thread thread = Thread.currentThread();
             obj.add(thread.getName());
            // obj.update(thread.getName());
         }
     }
    static class  ProducterUptate implements  Runnable{
         private SynObj obj;

         public ProducterUptate(SynObj obj) {
             this.obj = obj;
         }

         @Override
         public void run() {
             Thread thread = Thread.currentThread();
             obj.add(thread.getName());
         }
     }
}
