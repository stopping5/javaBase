package com.company.currentThread;

import java.util.concurrent.TimeUnit;

/**
 * @Classname: ThreadPoolDemo
 * @Description: 线程池测试
 * @Date: 2023/2/23 2:48 下午
 * @author: stopping
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolDemo t = new ThreadPoolDemo();
        for (int i = 0; i < 20; i++) {
            System.out.println("当前执行线程："+i);
            t.execute();
        }
        System.out.println("主线程执行:"+Thread.currentThread().getName());
    }

    public void execute(){
        ThreadPool.WORK_EXECUTOR.execute(()->{
            System.out.println("开始线程:"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程执行完毕:"+Thread.currentThread().getName());
        });
    }
}
