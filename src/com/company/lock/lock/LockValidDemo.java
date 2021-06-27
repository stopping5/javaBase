package com.company.lock.lock;

import java.util.concurrent.TimeUnit;

/**
 * @Description LockValidDemo
 * @Author stopping
 * @date: 2021/5/9 15:47
 */
class Phone{
    /**
     * 发送短信
     * */
    public static synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("发送短信");
    }
    /**
     * 发送邮件
     * */
    public synchronized void sendEmail(){
        System.out.println("发送邮件");
    }
}

public class LockValidDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone iPhone = new Phone();
        Phone huawei = new Phone();
        new Thread(()->{
            try {
                iPhone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
//            iPhone.sendEmail();
            huawei.sendEmail();
        },"A").start();
    //1. 两个加锁但是没有延迟
    //2. 类中非静态方法全部加锁，短信方法延时2s
        //发送短信
        //发送邮件
    //3. 类中一个非静态方法加锁、一个没有锁的普通方法
        //发送邮件
        //发送短信
    //4. 类中非静态方法全部加锁，两个线程调用两个实例化对象，锁失效。
        //发送邮件
        //发送短信

    //7.1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
        //发送邮件
        //发送短信
    //8.1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
        //发送邮件
        //发送短信
    }
}
