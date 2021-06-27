package com.company.designRule.OpenClosePrinciple;

/**
 * @Description JavaCourse
 * @Author stopping
 * @date: 2021/4/10 11:13
 */

public class JavaCourse implements ICourse{
    private Integer id;
    private String name;
    private Double price;

    public JavaCourse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
