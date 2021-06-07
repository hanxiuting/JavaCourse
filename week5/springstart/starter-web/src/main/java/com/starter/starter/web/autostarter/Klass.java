package com.starter.starter.web.autostarter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: hxt
 * @Date: 2021-06-05 11:07
 * @Description:
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "stu")
@ConditionalOnProperty(
        prefix = "stu",
        name = "info",
        havingValue = "true"
)
public class Klass {
    private List<Student> students;

    String dong() {
        return "Klass.dong -> student :" + this.students;
    }

    @Bean(name = "stu")
    public AutoStartService setStu() {
        return new AutoStartService(this.students);
    }
}
