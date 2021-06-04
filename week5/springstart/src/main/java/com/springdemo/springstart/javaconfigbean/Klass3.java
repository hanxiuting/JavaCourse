package com.springdemo.springstart.javaconfigbean;

import com.springdemo.springstart.autobean.Student2;
import com.springdemo.springstart.springaop.Klass;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:32
 * @Description:
 */
@Data
public class Klass3 {
    private Student3 student;

    @Autowired
    public Klass3(Student3 student) {
        this.student = student;
    }

    public void dong() {
        System.out.println("java config dong =======>");
        System.out.println("Klass1.dong -> student info:id =" + student.getId() + " ;name =" + student.getName());
    }
}
