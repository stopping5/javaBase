package com.company.currentThread;
import com.company.common.User;

/**
 * ThreadLocal测试
 */
public class ThreadLocalDemo {


    public static void main(String[] args) {

        fun();
        System.out.println("xxx");

//        for (int i = 0; i < 10; i++) {
//            ThreadPool.WORK_EXECUTOR.execute(()->{
//                UserContext userContext = new UserContext();
//                userContext.setUserContext();
//                userContext.getUserContext();
//            });
//        }
//        System.out.println("end");


    }

    public static void fun(){
        ThreadLocal tl = new ThreadLocal<Integer>(); //line1
        tl.set(100);   //line2
        tl.get();       //line3
    }

    /**
     * 用户上下文测试ThreadLocal是否线程隔离
     */
    static class UserContext{

        public ThreadLocal<User> USER_CONTEXT = new ThreadLocal<>();

        public void setUserContext(){
            USER_CONTEXT.set(new User(Thread.currentThread().getName(),"123"));
            System.out.println("设置User："+Thread.currentThread().getName());
        }

        public void getUserContext(){
           User user = USER_CONTEXT.get();
            System.out.println(user.toString());
        }
    }
}
