package com.company.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

/**
 * @Description ArrayListUnSafeDemo
 * @Author stopping
 * @date: 2021/5/9 17:31
 */

public class ArrayListUnSafeDemo {
    static CountDownLatch countDownLatch = new CountDownLatch(1000);
    public static void main(String[] args) throws InterruptedException {
        //listNotSafe();
        // setNotSafe();

        HashMap list = new HashMap();
        long start = System.currentTimeMillis();
        /*for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                list.put(finalI,UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }*/
        setNotSafe();
        countDownLatch.await();
        long end = System.currentTimeMillis() - start;
        System.out.println("cost time :"+end);
    }

    private static void setNotSafe() {
        //CopyOnWriteArraySet list = new CopyOnWriteArraySet<String>();
        Set list = Collections.synchronizedSet(new HashSet<>());
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("cost time :"+end);
    }

    private static void listNotSafe() {
        List<String> list =new CopyOnWriteArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("cost time :"+end);
    }
}
