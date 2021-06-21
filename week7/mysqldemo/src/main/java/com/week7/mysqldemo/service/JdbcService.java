package com.week7.mysqldemo.service;

import com.week7.mysqldemo.handler.ConnectionHandler;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: hxt
 * @Date: 2021-06-16 14:56
 * @Description:
 */
@Component
public class JdbcService {
    final long totalRows = 1000000;
    final String sql = "INSERT INTO market.tb_category\n" +
            "(name, create_time, update_time, delete_mark, creator, update_by)\n" +
            "VALUES(?, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, ?, ?, ?);\n";

    /**
     * 单一插入操作
     *
     * @throws SQLException
     */
    public void execute() throws SQLException {
        PreparedStatement ps;
        try {
            long startTime = System.currentTimeMillis();
            Connection conn = ConnectionHandler.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= totalRows; i++) {
                ps.setString(1, "test" + i);
                ps.setInt(2, 1);
                ps.setString(3, "hxt");
                ps.setString(4, "hxt");
                ps.execute();
            }
            System.out.println("execute 耗时：" + (System.currentTimeMillis() - startTime));
            ps.close();

        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionHandler.close();
        }
    }

    /**
     * 批量插入
     *
     * @throws SQLException
     */
    public void batchExecute() throws SQLException {
        Statement statement;
        try {
            long startTime = System.currentTimeMillis();
            Connection conn = ConnectionHandler.getConnection();
            statement = conn.createStatement();
            for (int i = 1; i <= totalRows; i++) {
                String sql = "INSERT INTO market.tb_category\n" +
                        "(name, create_time, update_time, delete_mark, creator, update_by)\n" +
                        "VALUES('test" + "i" + "', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 1,'hxt', 'hxt');\n";
                statement.execute(sql);
            }
            System.out.println("batchExecute 耗时：" + (System.currentTimeMillis() - startTime));
            statement.close();

        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionHandler.close();
        }
    }

    /**
     * 优化批量插入
     */
    public void addBatchExecute() throws SQLException {
        PreparedStatement ps;
        try {
            long startTime = System.currentTimeMillis();
            Connection conn = ConnectionHandler.getConnection();
            try {
                conn.setAutoCommit(false);
                ps = conn.prepareStatement(sql);
                for (int i = 1; i <= totalRows; i++) {
                    ps.setString(1, "test" + i);
                    ps.setInt(2, 1);
                    ps.setString(3, "hxt");
                    ps.setString(4, "hxt");

                    ps.addBatch();

                    if (i % 200 == 0) {
                        ps.executeBatch();
                        //清空batch
                        ps.clearBatch();
                    }
                }
                ps.executeBatch();
                conn.commit();
                System.out.println("addBatchExecute 耗时：" + (System.currentTimeMillis() - startTime));
                ps.close();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionHandler.close();
        }
    }
}
