package com.company.designRule.DependencyInversionPrinciple;

/**
 * @Description User
 * @Author stopping
 * @date: 2021/4/10 12:25
 */

public class User {
    private ICourse iCourse;
    //构造器方式
    /*public User(ICourse iCourse) {
        this.iCourse = iCourse;
    }*/
    //setter方式

    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    public void study(){
        iCourse.study();
    }
}
