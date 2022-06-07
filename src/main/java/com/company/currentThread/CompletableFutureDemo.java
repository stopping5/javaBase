package com.company.currentThread;

import java.util.concurrent.*;

/**
 * Completable
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        asyncCustomerResult();
    }

    /**
     * CompletableFuture.supplyAsync具有返回值的异步多线程
     * 可以通过Future#get 或者 CompletableFuture#whenComplete 获取返回值
     * CompletableFuture#exceptionally 处理异步逻辑的异常情况
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void supplyAsync() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //使用自定义线程池
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            return "hello supplyAsync";
        },executorService)
        //v-supplyAsync或exceptionally返回值,e-发送异常实体
        .whenComplete((v,e)->{
            System.out.println("获取返回值:"+v+",e="+e);
        })
        //异常情况处理
        .exceptionally(e ->{
            e.printStackTrace();
            System.out.println("异常情况:"+e.getCause()+"\t"+e.getMessage());
            return null;
        });
        //通过Future接口获取返回值
        System.out.println(stringCompletableFuture.get());
        //join和get功能是一样的，但是join不用抛异常
        System.out.println(stringCompletableFuture.join());
        //使用默认线程池
        //异常情况
            //当前线程名称：ForkJoinPool.commonPool-worker-9
            //获取返回值:null,e=java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
            //异常情况:java.lang.ArithmeticException: / by zero	java.lang.ArithmeticException: / by zero
        //正常情况
            //当前线程名称：ForkJoinPool.commonPool-worker-9
            //获取返回值:hello supplyAsync,e=null
            //hello supplyAsync
        executorService.shutdown();
    }

    /**
     * CompletableFuture 使用自定义线程池异步多线程
     * 1. 创建CompletableFuture<Void> 的静态方法 runAsync
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void runAsyncPool() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> runAsyncThreadPool = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
        }, executorService);
        //获取返回值
        System.out.println(runAsyncThreadPool.get());
        //当前线程名称：pool-1-thread-1
        //null
        executorService.shutdown();
    }

    /**
     * CompletableFuture 使用默认线程池异步多线程
     * 1. 创建CompletableFuture<Void> 的静态方法 runAsync
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void runAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(()->{
            System.out.println("当前线程名称："+Thread.currentThread().getName());
        });
        //获取返回值
        System.out.println(runAsync.get());
        //当前线程名称：ForkJoinPool.commonPool-worker-9
        //null
    }

    /**
     * 对于计算结果进行处理
     */
    private static void asyncCustomerResult(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //thenApply 对于计算结果存在串行的依赖关系，但是有异常的情况下会中断计算
        CompletableFuture.supplyAsync(()->{
            System.out.println("come in one "+Thread.currentThread().getName());
            return 1;
        },executorService).thenApply((t)->{
            int i = 10 / 0;
            System.out.println("come in tow "+Thread.currentThread().getName());
            return t+1;
        }).thenApply((t)->{
            System.out.println("come in three "+Thread.currentThread().getName());
            return t+2;
        }).whenComplete((t,e)->{
            System.out.println(e.getMessage());
            System.out.println("get result :"+t);
        });
        //handle thenApply 对于计算结果存在串行的依赖关系，但是有异常的情况不会中断计算，而是将异常传递。
        CompletableFuture.supplyAsync(()->{
            System.out.println("come in one "+Thread.currentThread().getName());
            return 1;
        },executorService).handle((t,e)->{
            System.out.println("come in tow "+Thread.currentThread().getName());
            int i = 10 / 0;
            return t+1;
        }).handle((t,e)->{
            System.out.println("come in three "+Thread.currentThread().getName());
            System.out.println(e.getMessage());
            return t+2;
        }).whenComplete((t,e)->{
            System.out.println("get result :"+t);
        });
        //thenApply
        //--------------------------------------------
        //come in one pool-1-thread-1
        //java.lang.ArithmeticException: / by zero
        //get result :null
        //---------------------------------------------
        //handle
        //---------------------------------------------
        //come in one pool-1-thread-2
        //come in tow main
        //come in three main
        //java.lang.ArithmeticException: / by zero
        //get result :null
    }
}
