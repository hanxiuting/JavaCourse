package com.springdemo.springstart.javaconfigbean;

import com.springdemo.springstart.iservice.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hxt
 * @Date: 2021-06-04 19:27
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.springdemo.springstart.javaconfigbean")
public class JavaConfigRun {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfigRun.class);
        ISchool school = context.getBean(ISchool.class);
        school.ding();
        Klass3 class3 = context.getBean(Klass3.class);
        class3.dong();
    }
}
