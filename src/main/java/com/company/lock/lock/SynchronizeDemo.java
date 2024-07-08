package com.company.lock.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stopping
 * @Description: TODO
 * @date 2024/7/8 5:52 PM
 */
public class SynchronizeDemo {

    public static class SynchronizeTask{
        /**
         * 通过synchronize可以通过类同步，控制一个类只能有一个线程执行调用
         *
         */
        public void fun(){
            synchronized (SynchronizeTask.class){
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("method = fun,thread-name = " + Thread.currentThread().getName()+", i = " + i);
                }
            }
        }

        public void fun1(){
            synchronized (SynchronizeTask.class) {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("method = fun1,thread-name = " + Thread.currentThread().getName() + ", i = " + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizeTask synchronizeTask1 = new SynchronizeTask();
        SynchronizeTask synchronizeTask2 = new SynchronizeTask();

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        executorService.submit(synchronizeTask1::fun);
        executorService.submit(synchronizeTask2::fun1);
        executorService.submit(synchronizeTask2::fun);

    }
}
