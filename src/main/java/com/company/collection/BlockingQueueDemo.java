package com.company.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
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
     * SynchronousQueue与一般的队列不同，它不算一种真正的队列，没有存储元素的空间，连存储一个元素的空间都没有。
     * 它的入队操作要等待另一个线程的出队操作，反之亦然。
     * 如果没有其他线程在等待从队列中接收元素，put操作就会等待。
     * take操作需要等待其他线程往队列中放元素，
     * 如果没有，也会等待。SynchronousQueue适用于两个线程之间直接传递信息、事件或任务。
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
        * 如果队列满则抛出异常
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
        * 添加信息到队列，如果队列满则返回false
        * 返回特殊值 - 是否成功
        * */
        boolean offerMsg(String message){
           return arrayBlockingQueue.offer(message);
        }
        /**
         * 添加信息到队列，如果队列满则在设置的时间内等待
         * - time 等待时间
         * 返回特殊值 - 是否成功
         * */
        boolean offerMsg(String message,long time) throws InterruptedException {
            return arrayBlockingQueue.offer(message,time, TimeUnit.SECONDS);
        }

        /**
         * 获取队列消息并且出队
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

   class MyDelayed<T> implements Delayed{

       /**
        * 携带数据
        * */
       private T data;
       /**
        * 延迟时间
        * */
       private long delayTime;
       /**
        * 过期时间
        * */
       private long expire;

       /**
        * 获取延迟时间 剩余时间 = 到期时间 - 当前时间
        * */
       @Override
       public long getDelay(TimeUnit unit) {
           return unit.convert(this.expire - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
       }

       /**
        * 比较器
        * 优先级规则：两个任务比较，时间短的优先执行
        * */
       @Override
       public int compareTo(Delayed o) {
           long f = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
           return (int) f;
       }

       public MyDelayed(T data, long delayTime) {
           this.data = data;
           this.delayTime = delayTime;
           this.expire = System.currentTimeMillis() + delayTime;
       }

       public T getData() {
           return data;
       }

       public void setData(T data) {
           this.data = data;
       }

       public long getDelayTime() {
           return delayTime;
       }

       public void setDelayTime(long delayTime) {
           this.delayTime = delayTime;
       }

       public long getExpire() {
           return expire;
       }

       public void setExpire(long expire) {
           this.expire = expire;
       }

       @Override
       public String toString() {
           return "MyDelayed{" +
                   "data=" + data +
                   ", delayTime=" + delayTime +
                   ", expire=" + expire +
                   '}';
       }
   }

   public void delayQueueDemo() throws InterruptedException {
       DelayQueue<MyDelayed> delayQueue = new DelayQueue();
       delayQueue.offer(new MyDelayed<>("delay Time1",10000),5000,TimeUnit.MILLISECONDS);
       delayQueue.offer(new MyDelayed<>("delay Time2",3000),5000,TimeUnit.MILLISECONDS);
       delayQueue.offer(new MyDelayed<>("delay Time3",2000),5000,TimeUnit.MILLISECONDS);
       Long start = System.currentTimeMillis();
       while (!delayQueue.isEmpty()){
           MyDelayed reuslt = delayQueue.take();
           Long end = System.currentTimeMillis();
           System.out.println("实际获取延迟时间"+(start-end)+"，获取的数据："+reuslt);
       }
//       实际获取延迟时间-2005，获取的数据：MyDelayed{data=delay Time3, delayTime=2000, expire=1663728740626}
//       实际获取延迟时间-3003，获取的数据：MyDelayed{data=delay Time2, delayTime=3000, expire=1663728741626}
//       实际获取延迟时间-10005，获取的数据：MyDelayed{data=delay Time1, delayTime=10000, expire=1663728748626}
   }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo();
        blockingQueueDemo.delayQueueDemo();

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

//        SynchronousQueueDemo synchronousQueue  = new SynchronousQueueDemo();
//        new Thread(()->{
//            System.out.println("put 1");
//            synchronousQueue.put("1");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("put 2");
//            synchronousQueue.put("2");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("put 3");
//            synchronousQueue.put("3");
//        },"AAA").start();
//
//        new Thread(()->{
//            System.out.println("take 1");
//            synchronousQueue.take();
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("take 2");
//            synchronousQueue.take();
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("take 3");
//            synchronousQueue.take();
//        },"BBB").start();
    }
}
