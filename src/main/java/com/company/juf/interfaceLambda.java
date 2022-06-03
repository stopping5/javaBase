package com.company.juf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description interfaceLambda 函数式接口单例
 * @Author stopping
 * @date: 2021/5/10 14:33
 */

public class interfaceLambda {
    public static void main(String[] args) {
        /*Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };*/
        Predicate<String> predicate = s -> {
            return s.isEmpty();
        };
        System.out.println(predicate.test(""));
    }

    private static void functionTest() {
        Function<String, List<String>> stringToList = s->{
           String [] ss = s.split("\\.");
           List<String> list = new ArrayList<>();
           for (String s1 : ss){
               list.add(s1);
           }
           return list;
        };
        stringToList.apply("1.2.3.4").forEach(s -> System.out.println(s));
    }

    private static void supplier() {
    /*Supplier<String> supplier = new Supplier<String>() {
        @Override
        public String get() {
            return null;
        }
    };*/
        Supplier<String> supplier = ()->{
            return "hello";
        };
        System.out.println(supplier.get());
    }

    private static void consumer() {
    /*Consumer consumer = new Consumer() {
        @Override
        public void accept(Object o) {

        }
    };*/
        Consumer<String> consumer = s -> {
            System.out.println("输入String字符串长度:"+s.length());
        };
        consumer.accept("hello");
    }
}
