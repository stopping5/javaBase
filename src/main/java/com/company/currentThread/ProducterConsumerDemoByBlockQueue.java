package com.company.currentThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description ProducterConsumerDemoByBlockQueue
 * 通过阻塞队列处理生产者消费者问题
 * @Author stopping
 * @date: 2021/5/9 12:08
 */
class ResourceData{
    /**
     * 是否执行生产标志位
     * */
    private boolean flag = true;
    /**
     * 控制生产变量
     * */
    private AtomicInteger integer = new AtomicInteger(0);

    /**
     * 阻塞队列
     * */
    private BlockingQueue<String> blockingQueue = null;

    public ResourceData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void product() throws InterruptedException {
        //生产消息
        String data = null;
        boolean prodResult;
        while (flag){
            data = integer.incrementAndGet()+"";
            prodResult = blockingQueue.offer(data,2, TimeUnit.SECONDS);
            if (prodResult){
                System.out.println(Thread.currentThread().getName()+"生产消息"+data+"成功！");
            }else{
                System.out.println(Thread.currentThread().getName()+"生产消息"+data+"失败！");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"stop product");
    }


    public void consumer() throws InterruptedException {
        //消费消息
        String data = null;
        while (flag){
            data = blockingQueue.poll(3, TimeUnit.SECONDS);
            if (null == data || data.equalsIgnoreCase("")){
                flag = false;
                System.out.println("stop consumer");
                return;
            }
            System.out.println(Thread.currentThread().getName()+",consumer data ="+data);
        }
        System.out.println(Thread.currentThread().getName()+"stop consumer");
    }
}

public class ProducterConsumerDemoByBlockQueue {
    public static void main(String[] args) {
        ResourceData resourceData = new ResourceData(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            try {
                resourceData.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            try {
                resourceData.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Consumer0").start();

        new Thread(()->{
            try {
                resourceData.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Consumer1").start();
    }
}
