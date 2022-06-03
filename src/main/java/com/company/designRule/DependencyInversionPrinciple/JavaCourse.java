package com.company.designRule.DependencyInversionPrinciple;

/**
 * @Description JavaCourse
 * @Author stopping
 * @date: 2021/4/10 13:00
 */

public class JavaCourse implements ICourse{
    @Override
    public void study() {
        System.out.println("i am study java");
    }
}
