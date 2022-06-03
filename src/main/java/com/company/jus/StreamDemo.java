package com.company.jus;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description StreamDemo 数据流计算案例
 * @Author stopping
 * @date: 2021/5/10 15:29
 */
class User{
    private int id;
    private String username;
    private int age;

    public User(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class StreamDemo {
    /**
     * 按条件筛选数据
     * 偶数ID
     * 年龄大于24
     * 用户名大写
     * 用户名字按照字母倒排序
     * 只输出一个用户名
     * */
    public static void main(String[] args) {
        User user1 = new User(1,"able",22);
        User user2 = new User(2,"bble",28);
        User user3 = new User(3,"cble",29);
        User user4 = new User(4,"dble",32);
        User user5 = new User(5,"eble",26);

        List<User> users = Arrays.asList(user1,user2,user3,user4,user5);

         List<String> s = users.stream()
                .filter(user -> user.getId()%2 == 0)
                .filter(user -> user.getAge() > 24)
                .map(user -> user.getUsername().toUpperCase())
                .sorted((t1,t2)->{
                    return t2.compareTo(t1);
                }).collect(Collectors.toList());
         System.out.println(s.size());
    }
}
