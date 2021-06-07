package com.starter.starter.web.jdbcDemo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @Author: hxt
 * @Date: 2021-06-05 16:02
 * @Description:
 */
public class Conn {
    private static Connection conn = null;

    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            String path = "src/main/resource/application.properties";
            prop.load(new FileReader(new File(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public static Connection getConnection() {
        try {
            Properties prop = getProperties();
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void close() {
        try {
            if (null != conn) {
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
