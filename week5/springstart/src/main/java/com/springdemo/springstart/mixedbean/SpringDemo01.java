package com.springdemo.springstart.mixedbean;

import com.springdemo.springstart.iservice.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo01 {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Student student123 = context.getBean(Student.class);

        Student student123 = (Student) context.getBean("student001");
        System.out.println(student123.toString());

        student123.print();

        Student student100 = (Student) context.getBean("student002");
        System.out.println(student100.toString());

        student100.print();

        Klass class1 = context.getBean(Klass.class);
        System.out.println(class1);
        System.out.println("Klass对象AOP代理后的实际类型：" + class1.getClass());
        System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类：" + (class1 instanceof Klass));

        ISchool school = context.getBean(ISchool.class);
        System.out.println(school);
        System.out.println("ISchool接口的对象AOP代理后的实际类型：" + school.getClass());

        ISchool school1 = context.getBean(ISchool.class);
        System.out.println(school1);
        System.out.println("ISchool接口的对象AOP代理后的实际类型：" + school1.getClass());
        school1.ding();
        class1.dong();
        System.out.println("   context.get BeanDefinitionNames() ===>> " + String.join(",", context.getBeanDefinitionNames()));

    }
}
