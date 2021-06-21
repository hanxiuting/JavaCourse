package com.week7.mysqldemo.controller;

import com.week7.mysqldemo.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @Author: hxt
 * @Date: 2021-06-16 14:56
 * @Description:
 */
@Controller
public class InsertBatchController {
    @Autowired
    JdbcService service;

    /**
     * 单一插入方式 插入100 0000条数据
     * 输出结果：
     * 超级慢，15min共添加25169条
     */
    @GetMapping("executeInsert")
    @ResponseBody
    public String executeInsert() throws SQLException {
        System.out.println("executeInsert begin ====>");
        service.execute();
        System.out.println("executeInsert end ====>");
        return "executeInsert execute success";
    }

    /**
     * 使用statement方式插入
     * 超级慢，15min共添加17673条
     * @return
     * @throws SQLException
     */
    @GetMapping("statementInsert")
    @ResponseBody
    public String statementInsert() throws SQLException {
        System.out.println("statementInsert begin ====>");
        service.batchExecute();
        System.out.println("statementInsert end ====>");
        return "executeInsert execute success";
    }

    /**
     * 使用addBatch事务方式 批量插入100 0000条记录
     * 输出结果：
     * executeAddBatch begin ====>
     * addBatchExecute 耗时：270059
     * executeAddBatch end ====>
     */
    @GetMapping("executeAddBatch")
    @ResponseBody
    public String executeAddBatch() throws SQLException {
        System.out.println("executeAddBatch begin ====>");
        service.addBatchExecute();
        System.out.println("executeAddBatch end ====>");
        return "executeAddBatch execute success";
    }


}
