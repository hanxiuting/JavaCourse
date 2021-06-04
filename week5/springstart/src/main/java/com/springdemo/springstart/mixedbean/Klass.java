package com.springdemo.springstart.mixedbean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class Klass {

    private List<Student> students;

    @Autowired
    public void dong() {
        System.out.println(this.getStudents());
    }
}
