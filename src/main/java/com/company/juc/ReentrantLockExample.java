package com.company.juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        lock.lock(); // 获取锁
        try {
            for (int i = 0; i < 10; i++) {
                count++; // 增加计数
                nestedIncrement(); // 调用嵌套方法，演示可重入
            }
        } finally {
            lock.unlock(); // 释放锁
        }
    }

    public void nestedIncrement() {
        // 再次尝试获取锁，当前线程可以成功获取，因为已经持有该锁
        lock.lock();
        try {
            count++;
            // 执行其他操作...
        } finally {
            lock.unlock(); // 必须释放锁，即使在嵌套方法中
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        example.increment();
        System.out.println("Final count is: " + example.count);
    }
}