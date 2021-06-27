package com.company.currentThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description ProductConsumerDemo
 * @Author stopping
 * @date: 2021/5/3 17:25
 */

public class ProductConsumerDemo {
    /**
     * 资源类 只能生产者生产一个、消费者最多消费一个
     * */
    static class SourceData{
        //初始化数据
        private int number = 0;
        //lock
        private Lock lock = new ReentrantLock();
        //条件
        private Condition condition = lock.newCondition();
        /**
         * 线程安全增加
         * */
        public void increment() throws InterruptedException {
            lock.lock();
            try{
                //1、判断
                while (number != 0){
                    //等待
                    condition.await();
                }
                //2、干活
                number++;
                System.out.println(Thread.currentThread().getName()+"\t"+number);
                //3、通知 this.notify
                condition.signalAll();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                lock.unlock();
            }
        }

        /**
         * 线程安全减少
         * */
        public void decrement() throws InterruptedException {
            lock.lock();
            try{
                //1、判断
                while (number == 0){
                    //等待
                    condition.await();
                }
                //2、干活
                number--;
                System.out.println(Thread.currentThread().getName()+"\t"+number);
                //3、通知 this.notify
                condition.signalAll();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                lock.unlock();
            }
        }
    }

    /**
     * 意图：通过一个初始化值为0的变量，多线程进行操作，保证生产数量为1
     * 1、线程 操作（方法） 资源类
     * 2、判断 干活 通知 (内聚到资源类)
     * 3、防止虚假唤醒 官方文档lock.await介绍 用户while判断
     * */
    public static void main(String[] args) {
        SourceData sourceData = new SourceData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    sourceData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        //减少线程
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    sourceData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    sourceData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CCC").start();

        //减少线程
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    sourceData.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DDD").start();
    }
}
//AAA	1
//BBB	0
//CCC	1
//BBB	0
//CCC	1
//BBB	0
//CCC	1
//BBB	0
//CCC	1
//BBB	0
//CCC	1
//DDD	0
//AAA	1
//DDD	0
//AAA	1
//DDD	0
//AAA	1
//DDD	0
//AAA	1
//DDD	0
//
//Process finished with exit code 0
//Process finished with exit code 0
