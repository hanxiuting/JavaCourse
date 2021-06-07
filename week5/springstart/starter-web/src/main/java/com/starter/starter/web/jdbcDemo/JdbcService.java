package com.starter.starter.web.jdbcDemo;

import com.starter.starter.web.autostarter.Student;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hxt
 * @Date: 2021-06-05 14:32
 * @Description:
 */
@Configuration
public class JdbcService {
    public void updateStu(String sql) {
        try {
            Connection conn = Conn.getConnection();
            Statement s = conn.createStatement();
            s.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conn.close();
        }
    }

    public List<Student> search(String sql) {
        List<Student> list = new ArrayList<>();
        try {
            Connection conn = Conn.getConnection();
            Statement s = conn.createStatement();
            ResultSet result = s.executeQuery(sql);
            if (result.next()) {
                Student stu = new Student();
                stu.setId(result.getInt("id"));
                stu.setName(result.getString("name"));
                list.add(stu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Conn.close();
        }
        return list;
    }

    /**
     * 事务
     *
     * @return
     */
    public void insertAndUpdateTrans() {
        String sql = "insert into student values(?,?)";
        String updSql = "update student set name = 'testTrans' where id = 1";
        PreparedStatement ps;
        try {
            Connection conn = Conn.getConnection();
            try {
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(sql);
                ps.setInt(1, 111);
                ps.setString(2, "test111");
                ps.execute();
                ps = conn.prepareStatement(updSql);
                ps.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conn.close();
        }
    }

}
