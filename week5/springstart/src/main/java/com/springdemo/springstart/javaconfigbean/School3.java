package com.springdemo.springstart.javaconfigbean;

import com.springdemo.springstart.iservice.ISchool;
import lombok.Data;

import javax.inject.Named;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:30
 * @Description:
 */
@Data
@Named("school3")
public class School3 implements ISchool {
    private final Klass3 class1;

//    public School3(Klass3 class1) {
//        this.class1 = class1;
//    }

    @Override
    public void ding() {
        System.out.println("java config ding=======>");
        System.out.println("School3.ding -> student info : id = " + class1.getStudent().getId() + " ;name =" + class1.getStudent().getName());
    }

}
