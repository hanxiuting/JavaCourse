package com.springdemo.springstart.javaconfigbean;

import com.springdemo.springstart.springaop.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hxt
 * @Date: 2021-06-04 19:42
 * @Description:
 */
@Configuration
public class SchoolCenter {
    @Bean
    public Student3 setStudentInfo() {
        Student3 stu = new Student3();
        stu.setId(7);
        stu.setName("Yellow");
        return stu;
    }

    @Bean
    public Klass3 class3(Student3 stu) {
        return new Klass3(stu);
    }
}
