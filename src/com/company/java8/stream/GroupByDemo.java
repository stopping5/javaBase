package com.company.java8.stream;

import com.company.pojo.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description Stream GroupByDemo 案例
 * @Author stopping
 * @date: 2021/6/29 23:33
 */

public class GroupByDemo {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User[]{
                new User("tom",18,"13141234567"),
                new User("job",20,"13141234567"),
                new User("gogo",18,"13141234567")
        });
        streamGroup(users);
        System.out.println("-------------------------------");
        userGroup(users);
    }
    /**
     * 非Stream group 实现
     * */
    private static void userGroup(List<User> users) {
        Map<Integer,List<User>> userMap = new HashMap<>();
        for (User u : users){
            Integer age = u.getAge();
            if (userMap.containsKey(age)){
                userMap.get(age).add(u);
            }else{
                List<User> user = new ArrayList<>();
                user.add(u);
                userMap.put(age,user);
            }
        }
        userMap.forEach((i,user)->{
            System.out.println("age:"+i+","+"user:"+user.toString());
        });
    }

    private static void streamGroup(List<User> users) {
        Map<Integer,List<User>> userMap = users.stream().collect(Collectors.groupingBy(User::getAge));
        userMap.forEach((i,user)->{
            System.out.println("age:"+i+","+"user:"+user.toString());
        });
    }
}
