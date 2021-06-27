package com.company.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Description ForkJoinTaskDemo 分支合并框架
 * Fork：把一个复杂任务进行分拆，大事化小
 * Join：把分拆任务的结果进行合并
 * @Author stopping
 * @date: 2021/5/10 17:45
 */
class MyTask extends RecursiveTask<Integer>{
    private int start;
    private int end;
    private int result;
    private int FORK_THRESHOLD = 10;

    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    protected Integer compute(){
        if ((end - start) <= FORK_THRESHOLD){
            for (int i = start; i <= end; i++) {
                result = result + i;
            }
        }
        //递归操作
        else{
            System.out.println("fork");
            int middle = (end + start) / 2;
            MyTask myTask1 = new MyTask(start,middle);
            MyTask myTask2 = new MyTask(middle+1,end);
            //递归操作
            myTask1.fork();
            myTask2.fork();
            //获取结果
            result = myTask1.join() + myTask2.join();
            System.out.println("get result :"+result);
        }
        return result;
    }
}
public class ForkJoinTasKDemo {
    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(new MyTask(0,10000));
        System.out.println(forkJoinTask.get());
        forkJoinPool.shutdown();
    }
}
