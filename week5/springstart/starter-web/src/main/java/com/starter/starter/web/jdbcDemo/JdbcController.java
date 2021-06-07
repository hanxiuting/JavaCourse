package com.starter.starter.web.jdbcDemo;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: hxt
 * @Date: 2021-06-05 15:37
 * @Description:
 */
@Controller
public class JdbcController {
    @Autowired
    public JdbcService service;

    @GetMapping("search")
    @ResponseBody
    public String search() {
        return JSON.toJSONString(service.search("select * from student"));
    }

    @GetMapping("updateOne")
    @ResponseBody
    public String updateOne() {
        service.updateStu("update name = test1 where id = 1");
        return "update one success";
    }

    @GetMapping("deleteOne")
    @ResponseBody
    public String deleteOne() {
        service.updateStu("delete from student where id = 1");
        return "delete one success";
    }

    @GetMapping("insertOne")
    @ResponseBody
    public String insertOne() {
        service.updateStu("insert student(id,name) values(1,'test')");
        return "insert one success";
    }

    @GetMapping("transOpt")
    @ResponseBody
    public String transOpt() {
        service.insertAndUpdateTrans();
        return "transaction success";
    }


}
