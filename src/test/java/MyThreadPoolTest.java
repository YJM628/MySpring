import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolTest {
    public static void main(String[] args) {
        //Executors.newScheduledThreadPool(1,ThreadFactoryBuilder.)
        Timer timer = new Timer();
        long delay = 1000;//1s  第一次延迟时间
        long intevalPeriod = 1000;  //1s 每1秒 执行一次
        for (int i = 0; i < 1000; i++) {
            timer.scheduleAtFixedRate(new UserTask(),delay,intevalPeriod);
        }
    }
    static  class  UserTask extends TimerTask {
        public void doSomeThing(Thread thread){
            System.out.println(Thread.currentThread().getName()+"-doSomeThing");
        }
        @Override
        public void run() {
            int corePoolSise=5;
            int maximumPoolSize=10;
            long keepAliveTime=1L;
            Random random = new Random();
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSise, maximumPoolSize,
                    keepAliveTime, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(3), new ThreadFactoryBuilder().setNameFormat(random.nextInt()+"contract-Thread").build());
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        doSomeThing(Thread.currentThread());
                        Thread.sleep(2000);//表示执行任务需要2秒
                        System.out.println("Thread-one,执行完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        doSomeThing(Thread.currentThread());
                        Thread.sleep(5000);
                        System.out.println("Thread-two,执行完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        doSomeThing(Thread.currentThread());
                        Thread.sleep(3000);
                        System.out.println("Thread-three,执行完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
