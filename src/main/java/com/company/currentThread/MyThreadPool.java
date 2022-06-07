package com.company.currentThread;

import java.util.concurrent.*;

/**
 * @Description MyThreadPool
 * @Author stopping
 * @date: 2021/5/8 0:43
 */

public class MyThreadPool {
    public static void main(String[] args) {
        /**
         * 核心线程：2
         * 线程池最大值：8
         * 等待时间：1s
         * 排队队列：3
         * 默认拒绝策略：直接抛出RejectedException异常阻止系统正常运行
         * 最大承载8线程
         * */
        ExecutorService threadPoolAbortPolicy = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        /**
         * 核心线程：2
         * 线程池最大值：8
         * 等待时间：1s
         * 排队队列：3
         * CallerRunPolity策略：调用者运行一种调节机制,该策略既不会抛弃任务,也不会抛出异常,而是将线程的调用退回到原始调用和线程上执行。
         * 最大承载8线程
         * */
        ExecutorService threadPoolCallerRunsPolicy = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        /**
         * 核心线程：2
         * 线程池最大值：8
         * 等待时间：1s
         * 排队队列：3
         * DiscardPolity策略：抛弃所有排队任务
         * 最大承载8线程
         * */
        ExecutorService threadPoolDiscardPolity = new ThreadPoolExecutor(
                2,
                5,
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());


        /**
         * 核心线程：2
         * 线程池最大值：8
         * 等待时间：1s
         * 排队队列：3
         * DiscardOldestPolicy：抛弃队列中等待最久的任务,然后把当前任务加入队列中尝试再次提交
         * 最大承载8线程
         * */
        ExecutorService threadPoolDiscardOldestPolity = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //模拟16个用户来办理业务 没有用户就是来自外部的请求线程.
        try {
            for (int i = 0; i < 11; i++) {
                threadPoolDiscardOldestPolity.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolDiscardOldestPolity.shutdown();
        }

    }
}
