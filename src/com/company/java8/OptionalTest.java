package com.company.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description OptionalTest
 * @Author stopping
 * @date: 2021/5/24 23:41
 */

public class OptionalTest {
    static class User{
        String username;
        String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public User() {
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        //userOptionalDemo();
        //ifParentDemo();
        User user = new User("xdp","123");
        User user1 = new User("xdp1","123");
        List<User> list = Arrays.asList(user,user,user1,user1);
    }

    private static void ifParentDemo() {
        User user = null;
        Optional.ofNullable(user).ifPresent(
            (u)->{
                System.out.println(u.toString());
            }
        );
    }

    private static void userOptionalDemo() {
        User user = null;
        User user1 = new User("xdp","123");
        User resultUser = Optional.ofNullable(user1).orElse(new User("default","0"));
        System.out.println("------------------"+Optional.ofNullable(user).isPresent());
        System.out.println(resultUser.toString());
    }
}
