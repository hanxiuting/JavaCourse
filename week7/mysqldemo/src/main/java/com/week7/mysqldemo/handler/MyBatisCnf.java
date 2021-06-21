package com.week7.mysqldemo.handler;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang.ArrayUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.omg.PortableInterceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@MapperScan()
public class MyBatisCnf {
    private static final Logger log = LoggerFactory.getLogger(MyBatisCnf.class);

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource master() {
        HikariDataSource druidDataSource = new HikariDataSource();
        return druidDataSource;
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave() {
        HikariDataSource druidDataSource = new HikariDataSource();
        return druidDataSource;
    }

    @Bean("setDataSource")
    public DataSource setDataSource() {
        DataSourceHandler dataSourceCnf = DataSourceHandler.getInstance();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master());
        dataSourceMap.put("slave", slave());

        log.info("dynamicDataSource dataSourceMap:{}", dataSourceMap);
        //指定master数据源
        dataSourceCnf.setDefaultDataSource(master());
        //指定数据源
        dataSourceCnf.setDataSources(dataSourceMap);
        return dataSourceCnf;
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        // 指定数据源
        sessionFactory.setDataSource(setDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/**/*Mapper.xml"));

        // 添加插件
        Interceptor[] interceptors = getPlugins();
        if (interceptors.length > 0) {
            sessionFactory.setPlugins();
        }

        return sessionFactory;
    }

    private Interceptor[] getPlugins() {
        Interceptor[] plugins = new Interceptor[0];

        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        return (Interceptor[]) ArrayUtils.add(plugins, pageInterceptor);
    }
}
