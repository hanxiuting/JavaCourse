package com.springdemo.springstart.autobean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:23
 * @Description:
 */
@Data
@Component
public class Student2 {
    @Value("005")
    private int id;
    @Value("Pink")
    private String name;
}
