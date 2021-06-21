package com.week7.mysqldemo.handler;

import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConnectionHandler {
    private static Connection conn = null;

    private static Properties getProperties() {
        Properties prop = new Properties();
        try {
            String path = "src/main/resources/application.properties";
            prop.load(new FileReader(new File(path)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    public static Connection getConnection() {
        try {
            Properties prop = getProperties();
            conn = DriverManager.getConnection(prop.getProperty("spring.datasource.url"), prop.getProperty("spring.datasource.username"), prop
                    .getProperty("spring.datasource.password"));
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

    public synchronized static void addDataSources(String database) {
        DataSourceHandler dynamicDataSource = DataSourceHandler.getInstance();
        Map<Object, Object> dynamicTargetDataSources = DataSourceHandler.targetDataSources;
        Set<Object> keySet = dynamicTargetDataSources.keySet();
        if (!keySet.contains(database)) {
            HikariDataSource druidDataSource = new HikariDataSource();
            Properties prop = getProperties();
            druidDataSource.setUsername("jdbc:mysql://localhost:3306/market" + database
                    + "?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;autoReconnectForPools=true&amp;failOverReadOnly=false");
            druidDataSource.setUsername(prop.getProperty("spring.datasource.username"));
            druidDataSource.setPassword(prop.getProperty("spring.datasource.password"));
            druidDataSource.setDriverClassName(prop.getProperty("spring.datasource.driver-class-name"));
            dynamicDataSource.addTargetDataSources(database, druidDataSource);
        }
    }
}
