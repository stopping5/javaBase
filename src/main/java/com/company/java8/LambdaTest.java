package com.company.java8;

import java.util.Comparator;
import java.util.function.*;

/**
 * @Description LambdaTets
 * @Author stopping
 * @date: 2021/5/26 0:24
 */
class User{
    private String username;
    private Integer age;

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public User() {
        System.out.println("create User");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(Integer age) {
        System.out.println("create User by age");
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

public class LambdaTest {

    public static void main(String[] args) {
    }

    private static void constructorMethodByAge() {
        Function<Integer,User> function = User::new;
        System.out.println(function.apply(18));
        //create User by age
        //User{username='null', age=18}
    }

    /**
     * 无参数构造函数引用
     * ClassName::new;
     * */
    private static void constructorMethod() {
        //ConstructorMethod();
        Supplier<User> newUser = ()->new User("xdp",18);
        System.out.println(newUser.get());
        System.out.println("***********************");
        Supplier<User> newUser1 = User::new;
        System.out.println(newUser1.get());
    }

    /**
     * boolean test(T t, U u);
     * boolean equals(Object anObject)
     * */
    private static void classMethod() {
        BiPredicate<String,String> bp = (x,y)-> x.equals(y);
        System.out.println("String bq:"+bp.test("x","x"));
        System.out.println("***********************");
        BiPredicate<String,String> bp1 = String::equals;
        System.out.println("String bq:"+bp1.test("xdp","x"));
    }

    /**
     * 对象::静态方法
     * Integer->int compare(int x, int y)
     * Comparator->int compare(T o1, T o2);
     * */
    private static void comparator() {
        Comparator<Integer> c = (x,y)-> Integer.compare(x,y);
        System.out.println("result:"+c.compare(2,2));
        System.out.println("***************************");
        Comparator<Integer> c1 = Integer::compare;
        System.out.println("result:"+c1.compare(1,2));
    }

    /**
     * 对象::非静态方法
     * Supplier::T get()
     * User::Integer getAge();
     * 这里的返回值Supplier和User 一致,没有入参也一致
     * 所以可以使用方法引用
     * */
    private static void getUserAgeLambda() {
        User user = new User("xdp",19);
        Supplier<Integer> supplier1 = ()->user.getAge();
        System.out.println("getAge1:"+supplier1.get());
        System.out.println("*************************");
        Supplier<Integer> supplier = user::getAge;
        System.out.println("getAge:"+supplier.get());
    }

    public static int len(String s1, Function<String,Integer> function){
        System.out.println("获取字符串"+s1);
        return function.apply(s1);
    }
}
