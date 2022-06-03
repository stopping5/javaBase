package com.company.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description SemaphoreDemo 信号量demo
 * 模拟资源为【停车位】
 * 线程为【车】
 * 现在有6辆车抢占3个停车位，如何实现变量互斥。
 * @Author stopping
 * @date: 2021/5/9 23:17
 */

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3,true);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢占停车位");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"离开停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
                },"车牌号:"+i).start();
        }
    }
}
