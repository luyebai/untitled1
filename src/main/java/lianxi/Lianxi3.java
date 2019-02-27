package lianxi;

import java.util.concurrent.*;

public class Lianxi3 {
    private static int count=1;//一个计数
    public static void main(String[] args) {
        Runnable rs= () -> {//实现一个runnable接口，得到runnable对象用于测试
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是一个线程"+Thread.currentThread().getName());
        };

        //自定义一个线程工厂
        ThreadFactory factory = r ->  new Thread(r,"线程"+count++);
        //自定义一个拒绝策略（当线程池没有空闲线程来执行新任务时触发）
        // r - 请求执行的可运行任务 executor - 执行者尝试执行此任务
        RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> System.out.println("哎呦！拒绝了！"+r.toString());
        /**使用构造函数创建一个线程池，也可以使用Executors工具类创建
         * corePoolSize 线程池维护线程的最少数量
         * * maximumPoolSize 线程池维护线程的最大数量
         * * keepAliveTime 线程池维护线程所允许的空闲时间
         * * workQueue 任务队列，用来存放我们所定义的任务处理线程
         * * threadFactory 线程创建工厂
         * * handler 线程池对拒绝任务的处理策略
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,3,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),factory,rejectedExecutionHandler
                );
        //提交测试
        for (int i = 0; i <100 ; i++) {
            executor.execute(rs);
        }
        //停止此线程池
        executor.shutdown();
    }

}
