package com.company.functioninterface;

/**
 * @Description FunctionInterfaceDemo
 * @Author stopping
 * @date: 2021/5/9 11:10
 */
@FunctionalInterface
interface Foo {
    int add(int x , int y);
    
    default void dev(){
        System.out.println("default dev");
    }
    
    static void mv(){
        System.out.println("static mv");
    }
}
/**
 * 函数式编程 Lambda
 * 1、口诀：拷贝小括号 写死箭头 实现大括号
 * 2、@FunctionInterface
 * 3、default java8新特性，8之前不允许有实现
 * 4、static
 * */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        //之前实现方式
        /*Foo foo = new Foo() {
            @Override
            public int add(int x, int y) {
                return 0;
            }
        };*/

        //lambda实现方式
        Foo foo = (x,y) -> {
            System.out.println("add method");
            return x+y;
        };
        System.out.println( "result:" + foo.add(1,1));

        foo.dev();

        Foo.mv();
    }
}
