package com.lxl.serializable;

import java.io.Serializable;

/**
 * 测试序列化类
 *
 * @author lxl
 */
public class Person implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7149501733150623806L;

    private int age;
    private String name;
    private String sex;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
