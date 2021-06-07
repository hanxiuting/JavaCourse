package com.springdemo.springstart.autobean;

import com.springdemo.springstart.iservice.ISchool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:30
 * @Description:
 */
@Data
@Component("school2")
public class School2 implements ISchool {
    private final Klass2 class1;

    @Autowired
    public School2(Klass2 class1) {
        this.class1 = class1;
    }

    @Override
    public void ding() {
        System.out.println("auto config ding =======>");
        System.out.println("School2.ding -> student info : id = " + class1.getStudent().getId() + " ;name =" + class1.getStudent().getName());
    }

}
