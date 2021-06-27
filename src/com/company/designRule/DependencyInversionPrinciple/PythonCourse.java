package com.company.designRule.DependencyInversionPrinciple;

/**
 * @Description PythonCourse
 * @Author stopping
 * @date: 2021/4/10 13:01
 */

public class PythonCourse implements ICourse{
    @Override
    public void study() {
        System.out.println("i am study python");
    }
}
