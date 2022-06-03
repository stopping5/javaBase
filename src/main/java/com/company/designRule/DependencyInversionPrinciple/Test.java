package com.company.designRule.DependencyInversionPrinciple;

/**
 * @Description Test
 * @Author stopping
 * @date: 2021/4/10 12:56
 */

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setiCourse(new PythonCourse());
        user.study();
    }
}
