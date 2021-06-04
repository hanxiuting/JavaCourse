package com.springdemo.springstart.autobean;

import com.springdemo.springstart.iservice.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Java Config注解实现bean
 *
 * @Author: hxt
 * @Date: 2021-06-04 18:06
 * @Description:
 */
@Configuration
@ComponentScan("com.springdemo.springstart.autobean")
public class AutoRun {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AutoRun.class);
        Klass2 class2 = context.getBean("class2", Klass2.class);
        class2.dong();
        ISchool school = context.getBean("school2", ISchool.class);
        school.ding();
    }
}
