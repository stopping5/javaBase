package com.company.designRule.OpenClosePrinciple;

/**
 * @Description JavaDiscountCourse java课程折扣类
 * @Author stopping
 * @date: 2021/4/10 11:16
 */

public class JavaDiscountCourse extends JavaCourse{
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginPrice(){
        return super.getPrice();
    }

    @Override
    public Double getPrice(){
        return super.getPrice() * 0.6;
    }
}
