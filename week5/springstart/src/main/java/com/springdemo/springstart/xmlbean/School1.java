package com.springdemo.springstart.xmlbean;

import com.springdemo.springstart.iservice.ISchool;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:30
 * @Description:
 */
public class School1 implements ISchool {
    private Klass1 class1;

    public School1() {

    }

    public School1(Klass1 class1) {
        this.class1 = class1;
    }

    @Override
    public void ding() {
        System.out.println("School1.ding -> student info : id = " + class1.getStudent().getId() + " ;name =" + class1.getStudent().getName());
    }

    public Klass1 getClass1() {
        return class1;
    }

    public void setClass1(Klass1 class1) {
        this.class1 = class1;
    }
}
