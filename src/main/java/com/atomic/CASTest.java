package com.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ACS原理探索
 * ABA问题如何解决
 */
public class CASTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        /**
         *     UnSafe类实现
         *     //obj: 第一个参数是要操作的对象的实例。在Java中，所有的整数字段都是某个对象的一部分，因此需要提供该整数字段所在的对象的引用。
         *     //offset: 第二个参数是对象中需要操作的字段（在这种情况下是一个整数字段）的内存偏移量。这个偏移量是从对象的起始内存地址到该字段实际存储位置的距离。Unsafe 类提供了 objectFieldOffset 方法来获取一个特定字段的偏移量。
         *     //expected: 第三个参数是期望值，即在执行比较操作时，我们期望字段的当前值是什么。
         *     //updated: 第四个参数是更新值，如果字段的当前值与期望值相等，字段的值将被更新为这个值。
         *     public final int getAndAddInt(Object var1, long var2, int var4) {
         *         int var5;
         *         do {
         *             //获取
         *             var5 = this.getIntVolatile(var1, var2);
         *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
         *
         *         return var5;
         *     }
         */
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println("get increment num = " + andIncrement);

        ABAProblem();
    }


    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1, 0);

    public static void ABAResolve() throws InterruptedException {
        System.out.println("初始值：" + atomicStampedRef.getReference() + "，版本号：" + atomicStampedRef.getStamp());
        // 线程1执行CAS操作，期望值为1，新值为2，版本号为0
        Thread thread1 = new Thread(() -> {
            int stamp = atomicStampedRef.getStamp();
            atomicStampedRef.compareAndSet(1, 2, stamp, stamp + 1);
        });

        // 线程2执行CAS操作，期望值为2，新值为1，版本号为1
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = atomicStampedRef.getStamp();
            atomicStampedRef.compareAndSet(2, 1, stamp, stamp + 1);
        });

        thread1.start();
        thread2.start();
        //等待线程执行完毕
        thread1.join();
        thread2.join();
        System.out.println("最终值：" + atomicStampedRef.getReference() + "，版本号：" + atomicStampedRef.getStamp());
    }

    static AtomicInteger account = new AtomicInteger(0);

    public static void ABAProblem() throws InterruptedException {
        // 线程1，模拟取款操作
        Thread thread1 = new Thread(() -> {
            account.getAndAdd(100); // 模拟取走100元
        });

        // 线程2，模拟存款操作
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.getAndAdd(-100); // 模拟存入100元
        });

        // 线程3，模拟再次取款操作
        Thread thread3 = new Thread(() -> {
            int currentVal = account.get();
            if (currentVal == 0) {
                // 这里可能发生ABA问题，因为值从0变到-100，再变回0，看起来没有变化
                account.getAndAdd(-100);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join(); // 等待thread1完成
        thread3.start();
        thread3.join(); // 等待thread3完成
        thread2.join();

        System.out.println("Final account balance: " + account.get());
    }
}
