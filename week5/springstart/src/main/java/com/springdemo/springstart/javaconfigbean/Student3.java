package com.springdemo.springstart.javaconfigbean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: hxt
 * @Date: 2021-06-04 17:23
 * @Description:
 */
@Data
public class Student3 {
    private int id;
    private String name;
}
