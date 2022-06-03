package com.company.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description JMMToVolatileDemo 验证volatile可见性
 * @Author stopping
 * @date: 2021/4/19 14:18
 */
class MyDate{
    /**
     * volatile
     * 保证可见性
     * 不保证原子性
     *  - synchronize
     *  - AtomicInteger
     * */
    private    int age = 0;

    private AtomicInteger aage = new AtomicInteger();

    public AtomicInteger getAage() {
        return aage;
    }

    public void setAage(AtomicInteger aage) {
        this.aage = aage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 自加方法
     * 不保证原子性
     * */
    public synchronized void incAge(){
        age++;
    }

    public void atomicIncAge(){
        aage.incrementAndGet();
    }
}
public class JMMToVolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        //可见性测试
        canSeePropertiesValid();
        //原子性测试
        //atomicVolatileValid();
    }

    private static void atomicVolatileValid() {
        long currentTime = System.currentTimeMillis();
        MyDate myDate = new MyDate();
        for (int i = 1; i <= 40; i++) {
            new Thread(()->{
                for (int j = 1; j <= 10000; j++) {
                    myDate.incAge();
                    //myDate.atomicIncAge();
                }
            },"Thread-"+i).start();
        }

        //只有剩主线程时才打印
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        long costTime = System.currentTimeMillis() - currentTime;
        System.out.println("Increase Age result : "+ myDate.getAge()+",cost time : "+costTime);
        //System.out.println("Increase AAge result : "+ myDate.getAage()+",cost time : "+costTime);
    }

    /**
     * @Description 验证Volatile可见性
     */
    private static void canSeePropertiesValid() {
        MyDate myDate = new MyDate();
        new Thread(()->{
            System.out.println("come in thread A");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myDate.setAge(20);
            System.out.println(Thread.currentThread().getName()+",update age = "+ myDate.getAge());
        },"Thread-A").start();
        //main thread
        System.out.println(Thread.currentThread().getName()+",start read myDate#age:"+myDate.getAge());
        while (myDate.getAge() == 0){
        }
        System.out.println(Thread.currentThread().getName()+",update read myDate#age:"+myDate.getAge());
    }

}
