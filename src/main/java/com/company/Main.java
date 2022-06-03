package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        int num = 0;
	// write your code here
        do {
            num++;
            System.out.println("当前数字："+num);
        }while (num < 5);{
            System.out.println("执行自增");
        }
        Executors.newFixedThreadPool(1);
    }
}
