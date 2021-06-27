package com.company.lock.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description ConditionDemo
 * 意图：线程A/B/C 分别对应 1/2/3 执行顺序是 A->B->C
 * @Author stopping
 * @date: 2021/5/3 18:00
 */

public class ConditionDemo {
    static class ShareResource{
        private int number = 1;
        private Lock lock = new ReentrantLock();
        private Condition ca = lock.newCondition();
        private Condition cb = lock.newCondition();
        private Condition cc = lock.newCondition();

        public void executeA(){
            lock.lock();
            try{
                //判断
                while (number != 1){
                    ca.await();
                }
                //操作
                number = 2;
                System.out.println(Thread.currentThread().getName());
                //唤醒
                cb.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void executeB(){
            lock.lock();
            try{
                //判断
                while (number != 2){
                    cb.await();
                }
                //操作
                number = 3;
                System.out.println(Thread.currentThread().getName());
                //唤醒
                cc.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void executeC(){
            lock.lock();
            try{
                //判断
                while (number != 3){
                    cc.await();
                }
                //操作
                number = 1;
                System.out.println(Thread.currentThread().getName());
                //唤醒
                ca.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public static void main(String[] args) {
            ShareResource shareResource = new ShareResource();
            new Thread(()->{
                shareResource.executeC();
            },"C").start();

            new Thread(()->{
                shareResource.executeA();
            },"A").start();

            new Thread(()->{
                shareResource.executeB();
            },"B").start();
        }
    }


}
