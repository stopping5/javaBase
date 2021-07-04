package com.company.pojo;

/**
 * @Description User
 * @Author stopping
 * @date: 2021/6/29 23:34
 */
public class User {
    /**
     * 用户名
     */
    private String username;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号
     */
    private String phone;

    public User(String username, Integer age, String phone) {
        this.username = username;
        this.age = age;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}