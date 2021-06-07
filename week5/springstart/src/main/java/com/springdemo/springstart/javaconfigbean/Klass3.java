package com.springdemo.springstart.javaconfigbean;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
        System.out.println("Klass3.dong -> student info:id =" + student.getId() + " ;name =" + student.getName());
    }
}
