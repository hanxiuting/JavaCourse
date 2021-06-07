package com.starter.starter.web.autostarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: hxt
 * @Date: 2021-06-05 11:17
 * @Description:
 */
@Controller
@EnableAutoConfiguration
public class AutoStarterController {
    @Autowired
    private School school;
    @Autowired
    private Klass class1;

    @Resource(name = "stu")
    private AutoStartService service;

    @GetMapping("getDong")
    @ResponseBody
    public String getDong() {
       return class1.dong();
    }

    @GetMapping("getDing")
    @ResponseBody
    public String getDing() {
        return school.ding();
    }

    @GetMapping("stuInfo")
    @ResponseBody
    public String getStuInfo() {
        return service.getStudentsInfo();
    }
}
