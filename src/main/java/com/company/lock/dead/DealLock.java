package com.company.lock.dead;

import java.util.concurrent.TimeUnit;

/**
 * @Description DealLock
 * @Author stopping
 * @date: 2021/5/8 12:06
 */

public class DealLock implements Runnable{

    private String lockA;
    private String lockB;

    public DealLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println("holdLock:"+lockA+",tryLock:"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println("holdLock:"+lockB+",tryLock:"+lockA);
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DealLock(lockA,lockB),"thread-a").start();
        new Thread(new DealLock(lockB,lockA),"thread-B").start();
    }
}
