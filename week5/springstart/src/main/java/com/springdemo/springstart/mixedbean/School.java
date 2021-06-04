package com.springdemo.springstart.mixedbean;

import com.springdemo.springstart.iservice.ISchool;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class School implements ISchool {

    // Resource 
    @Autowired(required = true) //primary
            Klass class1;

    @Resource(name = "student002")
    Student student100;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);

    }

}
