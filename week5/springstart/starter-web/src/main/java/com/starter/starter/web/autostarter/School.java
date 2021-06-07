package com.starter.starter.web.autostarter;

import com.starter.starter.web.iservice.ISchool;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hxt
 * @Date: 2021-06-05 11:06
 * @Description:
 */
@Configuration
public class School implements ISchool {
    private Klass class1;

    public School() {
    }

    public School(Klass class1) {
        this.class1 = class1;
    }

    @Override
    public String ding() {
        return "School1.ding -> student info : " + class1.getStudents().size();
    }
}
