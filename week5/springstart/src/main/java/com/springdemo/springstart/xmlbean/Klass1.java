package com.springdemo.springstart.xmlbean;

import com.springdemo.springstart.springaop.Klass;
import com.springdemo.springstart.springaop.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:32
 * @Description:
 */
public class Klass1 {
    private Student1 student;

    public Klass1() {
    }

    public Klass1(Student1 student) {
        this.student = student;
    }

    public Student1 getStudent() {
        return student;
    }

    public void setStudent(Student1 student) {
        this.student = student;
    }

    public void dong() {
        System.out.println("Klass1.dong -> student info:id =" + student.getId() + " ;name =" + student.getName());
    }
}
