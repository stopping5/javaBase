package com.company.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description StreamTest
 * @Author stopping
 * @date: 2021/5/27 21:52
 */
//employee
class Employee{
    //姓名
    private String name;
    //年龄
    private int age;
    //薪资
    private Double salary;

    public Employee(String name, int age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

public class StreamTest {
    static List<Employee> getEmployees(){
        Employee e1 = new Employee("张三",18,8000.00);
        Employee e2 = new Employee("张4",13,8011.00);
        Employee e3 = new Employee("张5",28,18000.00);
        Employee e4 = new Employee("张6",19,8400.00);
        Employee e5 = new Employee("张7",38,7000.00);
        Employee e6 = new Employee("张8",15,8200.00);
        Employee e7 = new Employee("张9",22,9000.00);
        Employee e9 = new Employee("张9",22,9000.00);
        Employee e8 = new Employee("张9",22,9000.00);
        return Arrays.asList(e1,e2,e3,e4,e5,e6,e7);
    }
    public static void main(String[] args) {
        List<Employee> employees =  getEmployees();
        //创建流
        //中间操作
        //filter
        //filterDemo(employees);

        //分片 limit/skip/distinct
        //LIMIT 限制条数（短路）
        //skip 跳过
        //limitAndSkipDemo(employees);
        //distinct 去重
        //employees.stream().map(Employee::getName).distinct().forEach(System.out::println);

        //map 映射相当于把某一列的值拿出来
        //mapDemo(employees);
        //flatMapDemo();


        //18 - 13 - 28 - 19 - 38 - 15 - 22
        //sore
    }

    private static void flatMapDemo() {
        System.out.println("Map实现···");
        List<String> s = Arrays.asList("aaa","bbb","ccc");
        //map实现每个元素字符串转化为字符流输出
        Stream<Stream<Character>> ssc = s.stream().map(StreamTest::flatMapStringToChar);
        ssc.forEach((sc)->{
            sc.forEach(System.out::println);
        });
        System.out.println("flatMap实现···");
        //flatMap 实现每个元素字符串转化为字符流输出
        s.stream().flatMap(StreamTest::flatMapStringToChar).forEach(System.out::println);
        //Map实现···
        //a
        //a
        //a
        //b
        //b
        //b
        //c
        //c
        //c
        //flatMap实现···
        //a
        //a
        //a
        //b
        //b
        //b
        //c
        //c
        //c
    }

    public static Stream<Character> flatMapStringToChar(String s){
        List<Character> lc = new ArrayList<>();
        for (Character c : s.toCharArray()){
            lc.add(c);
        }
        return lc.stream();
    }
    private static void mapDemo(List<Employee> employees) {
        employees.stream().map(Employee::getAge).forEach(System.out::println);
    }

    private static void limitAndSkipDemo(List<Employee> employees) {
        System.out.println("获取前4条数据");
        employees.stream().limit(4).forEach(System.out::println);
        System.out.println("跳过前4条数据");
        employees.stream().skip(4).forEach(System.out::println);
        //获取前4条数据
        //Employee{name='张三', age=18, salary=8000.0}
        //Employee{name='张4', age=13, salary=8011.0}
        //Employee{name='张5', age=28, salary=18000.0}
        //Employee{name='张6', age=19, salary=8400.0}
        //跳过前4条数据
        //Employee{name='张7', age=38, salary=7000.0}
        //Employee{name='张8', age=15, salary=8200.0}
        //Employee{name='张9', age=22, salary=9000.0}
    }

    private static void filterDemo(List<Employee> employees) {
        employees.stream()
            //年龄大于18
            .filter((e)->{
                System.out.println("筛选大于18");
                return  e.getAge() > 18;
            })
            //并且薪资大于8000
            .filter((e)->{
                System.out.println("筛选大于8000");
                return e.getSalary() > 8000;
            })
            .limit(2)//&& 操作
            .forEach(
                (e)->{
                    System.out.println(e.toString());
                }
        );
        //筛选大于18
        //筛选大于18
        //筛选大于18
        //筛选大于8000
        //Employee{name='张5', age=28, salary=18000.0}
        //筛选大于18
        //筛选大于8000
        //Employee{name='张6', age=19, salary=8400.0}
    }
}
