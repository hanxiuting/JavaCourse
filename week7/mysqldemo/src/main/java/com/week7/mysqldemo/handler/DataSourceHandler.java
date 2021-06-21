package com.week7.mysqldemo.handler;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


public class DataSourceHandler extends AbstractRoutingDataSource {
    public static Map<Object, Object> targetDataSources = new HashMap<>();

    private static DataSourceHandler dataSourceHandler = null;

    private DataSourceHandler() {

    }

    public static synchronized DataSourceHandler getInstance() {
        return null == dataSourceHandler ? new DataSourceHandler() : dataSourceHandler;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return super.determineTargetDataSource();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHandler.getDataSourceKey();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        DataSourceHandler.targetDataSources = targetDataSources;
    }

    public void addTargetDataSources(Object key, Object dataSource) {
        targetDataSources.put(key, dataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    public void setDefaultDataSource(Object defaultDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    public void setDataSources(Map<Object, Object> dataSources) {
        setTargetDataSources(dataSources);
    }
}
