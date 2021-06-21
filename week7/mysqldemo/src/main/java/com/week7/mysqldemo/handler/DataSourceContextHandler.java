package com.week7.mysqldemo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DataSourceContextHandler {

    private static final Logger log = LoggerFactory.getLogger(DataSourceContextHandler.class);

    private static final ThreadLocal<String> hanlder = new ThreadLocal<String>() {
        /**
         * 将 master 数据源的 key作为默认数据源的 key
         */
        @Override
        protected String initialValue() {
            return "master";
        }
    };

    /**
     * 切换数据源
     *
     * @param key
     *            数据源
     */
    public static void setDataSourceKey(String key) {
        if (!StringUtils.isEmpty(key)) {
            // 当数据源不存在时，添加数据源
            ConnectionHandler.addDataSources(key);

            hanlder.set(key);
        }
    }
    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSourceKey() {
        return hanlder.get();
    }

    /**
     * 重置数据源
     */
    public static void clearDataSourceKey() {
        hanlder.remove();
    }
}
