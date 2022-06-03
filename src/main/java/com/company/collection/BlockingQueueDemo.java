package com.company.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description BlockingQueueDemo 模拟消息场景
 * @Author stopping
 * @date: 2021/5/3 14:54
 */

public class BlockingQueueDemo {
    /**
     * 同步阻塞
     * */
    static class SynchronousQueueDemo{
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        public void put(String message) {
            try {
                synchronousQueue.put(message);
            }catch (Exception e){
                System.out.println("ex");
            }
        }

        public String take(){
            try{
                return synchronousQueue.take();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return "";
        }
    }
    /**
     * ArrayBlockingQueue
     * */
   static class ArrayBlockDemo{
       ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(3);

       /**
        * 添加信息到队列
        * Throws exception
        * */
       void addMsg(String message){
           arrayBlockingQueue.add(message);
       }

       /**
        * 获取头信息并删除
        * Throws exception
        * */
       String getMsg(){
           return arrayBlockingQueue.remove();
       }

       /**
        * 检索队列头信息
        * Throws exception
        * */
       String checkMsg(){
           return arrayBlockingQueue.peek();
       }

       /**
        * 添加信息到队列
        * 返回特殊值 - 是否成功
        * */
        boolean offerMsg(String message){
           return arrayBlockingQueue.offer(message);
        }
        /**
         * 添加信息到队列 - time 等待时间
         * 返回特殊值 - 是否成功
         * */
        boolean offerMsg(String message,long time) throws InterruptedException {
            return arrayBlockingQueue.offer(message,time, TimeUnit.SECONDS);
        }

        /**
         * 获取队列消息
         * */
        String pollMsg(){
            return arrayBlockingQueue.poll();
        }
        /**
         * 获取队列消息
         * time - 等待时间
         * */
        String pollMsg(long time) throws InterruptedException {
            return arrayBlockingQueue.poll(time,TimeUnit.SECONDS);
        }

        /**
         *  添加消息
         *  阻塞
         * */
        void put(String message){
            try {
                arrayBlockingQueue.put(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**
         * 获取队列消息
         * 阻塞
         * */
        String take(){
            try {
                return arrayBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }
   }

    public static void main(String[] args) throws InterruptedException {
        /*ArrayBlockDemo arrayBlockDemo = new ArrayBlockDemo();
        //Process finished with exit code 0
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+arrayBlockDemo.take());
            System.out.println(Thread.currentThread().getName()+":"+arrayBlockDemo.take());
            System.out.println(Thread.currentThread().getName()+":"+arrayBlockDemo.take());
            System.out.println(Thread.currentThread().getName()+":"+arrayBlockDemo.take());
            System.out.println(Thread.currentThread().getName()+":"+arrayBlockDemo.take());
        }).start();
        arrayBlockDemo.put("a");
        arrayBlockDemo.put("b");
        arrayBlockDemo.put("c");
        arrayBlockDemo.put("d");
        System.out.println("enable take thread");

        //Thread-0:a
        //enable take thread
        //Thread-0:b
        //Thread-0:c
        //Thread-0:d*/

        SynchronousQueueDemo synchronousQueue  = new SynchronousQueueDemo();
        new Thread(()->{
            System.out.println("put 1");
            synchronousQueue.put("1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put 2");
            synchronousQueue.put("2");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put 3");
            synchronousQueue.put("3");
        },"AAA").start();

        new Thread(()->{
            System.out.println("take 1");
            synchronousQueue.take();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("take 2");
            synchronousQueue.take();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("take 3");
            synchronousQueue.take();
        },"BBB").start();
    }
}
