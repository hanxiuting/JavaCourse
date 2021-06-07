package com.starter.starter.web.autostarter;

import java.util.List;

/**
 * @Author: hxt
 * @Date: 2021-06-05 11:26
 * @Description:
 */
public class AutoStartService {
    private List<Student> students;

    AutoStartService(List<Student> students) {
        this.students = students;
    }

    String getStudentsInfo() {
        StringBuilder sBuilder = new StringBuilder();
        for (Student item : students) {
            sBuilder.append(item.getId() + ":" + item.getName() + "\n");
        }
        return sBuilder.toString();
    }

}
