package com.springdemo.springstart.xmlbean;

import com.springdemo.springstart.iservice.ISchool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:45
 * @Description:
 */
public class XmlRun {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("xmlbean.xml");
        Klass1 class1 = context.getBean(Klass1.class);
        class1.dong();

        ISchool school = context.getBean(ISchool.class);
        school.ding();
    }
}
