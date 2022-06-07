package com.company.currentThread;

import java.util.concurrent.*;

/**
 * FutureTask demo
 * 周阳JUC https://www.bilibili.com/video/BV1ar4y1x727?p=10
 * 模拟场景：多线程（runnable）+异步（future）+返回值（callable）
 * ## 1. 什么要使用Future
 * 1. Thread 构造只支持runnable作为参数
 * 2. 但是runnable不支持返回值故选择callable
 * 3. FutureTask继承Future、Runnable,并且构造函数支持callable
 */
public class FutureTaskDemo {
    /**
     * 多线程具有返回值类
     */
    public static class OrderThread implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("开始组装订单数据...");
            //休眠线程1s
            Thread.sleep(1000);
            return "已处理订单数据";
        }
    }

    /**
     * 多线程具有返回值类
     */
    public static class SendThread implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("开始发送消息通知...");
            //休眠线程1s
            Thread.sleep(2000);
            return "消息通知完毕";
        }
    }

    /**
     * 单一异步任务处理
     */
    public static void singleThread(){
        System.out.println("页面数据组装开始...");
        FutureTask<String> futureTask = new FutureTask<String>(new OrderThread());
        Thread t1 = new Thread(futureTask,"t1");
        t1.start();
        try {
            System.out.println(futureTask.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("main thread end!");
    }

    /**
     * 多线程处理数据,同时开启两个线程处理订单数据和发送消息的功能
     * 页面数据组装开始...
     * 开始组装订单数据...
     * 开始发送消息通知...
     * 消息通知完毕
     * 已处理订单数据
     * main thread end!
     */
    public static void mulThread(){
        System.out.println("页面数据组装开始...");
        FutureTask<String> orderTask = new FutureTask<String>(new OrderThread());
        FutureTask<String> sendTask = new FutureTask<String>(new SendThread());
        ExecutorService threadPoolAbortPolicy = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        threadPoolAbortPolicy.submit(orderTask);
        threadPoolAbortPolicy.submit(sendTask);
        try {
            System.out.println(sendTask.get());
            System.out.println(orderTask.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("main thread end!");
        threadPoolAbortPolicy.shutdown();
    }

    public static void main(String[] args) {
        //singleThread();

        mulThread();
    }
}
