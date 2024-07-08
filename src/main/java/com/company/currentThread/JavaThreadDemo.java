package com.company.currentThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author stopping
 * @Description: Java多线程基本能力，例如开启多线程进行灵活调用。区别Synchronize、Lock的运用。
 * @date 2024/7/8 11:06 AM
 */
public class JavaThreadDemo {

    /**
     * 实现Runnable接口，实现异步任务
     * 通过Thread承载调用多线程，也可以通过内部类实现无需声明该类
     */
    static class RunnableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("runnable thread-name = "+Thread.currentThread().getName());
        }
    }

    /**
     * 继承Thread重新run接口实现异步任务
     */
    static class ThreadTask extends Thread{
        @Override
        public void run() {
            System.out.println("thread thread-name = "+Thread.currentThread().getName());
        }
    }

    /**
     * 实现Callable接口，有返回值，异常等场景
     * Thread需要通过FutureTask参数运行，Future异步线程
     * FutureTask需要实现Callable相关类
     */
    static class FutureTaskDemo implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("callable thread-name = "+Thread.currentThread().getName());
            return Thread.currentThread().getName();
        }
    }


    public static void main(String[] args) {
        //thread
        ThreadTask threadTask = new ThreadTask();
        threadTask.start();

        //runnable
        new Thread(new RunnableTask(),"runnable-task").start();

        //Future
        FutureTask<String> stringFuture = new FutureTask<String>(new FutureTaskDemo());
        new Thread(stringFuture,"future-task").start();
        try {
            System.out.println(stringFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
