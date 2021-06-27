package com.company.lock.spinlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description SpinLock 自旋锁案例
 * @Author stopping
 * @date: 2021/4/18 11:13
 */

public class SpinLock {
    AtomicReference<Thread> atomicReference =  new AtomicReference();

    /**
     * @Description T0D0 spinLock
     * first thread -> 获取当前线程判断原子引用中是为null，null则将线程保存AtomicReference
     */
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println("thread name"+thread.getName()+thread.getId()+"-> LOCK");
        //CAS期望null的时候保存获取锁的线程
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println("thread name"+thread.getName()+thread.getId()+"-> UNLOCK");
    }
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            spinLock.myLock();
            try {
                System.out.println("sleeping");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnlock();
        });

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.execute(()->{
            spinLock.myLock();
            try {
                System.out.println("sleeping");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.myUnlock();
        });
    }
}
