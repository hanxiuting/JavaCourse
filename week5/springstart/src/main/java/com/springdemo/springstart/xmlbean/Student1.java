package com.springdemo.springstart.xmlbean;


/**
 * @Author: hxt
 * @Date: 2021-06-04 17:23
 * @Description:
 */
public class Student1 {
    private int id;
    private String name;

    public Student1() {
    }

    public Student1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
