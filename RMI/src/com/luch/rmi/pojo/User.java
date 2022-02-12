package com.luch.rmi.pojo;

import java.io.Serializable;

/**
 * @author luch
 * @date 2021/11/22 22:37
 */
public class User implements Serializable {
    private int id;
    private String name;

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
