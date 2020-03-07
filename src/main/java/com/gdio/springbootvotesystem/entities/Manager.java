package com.gdio.springbootvotesystem.entities;

/**
 * @author gdio
 * @create 2020-02-22 17:36
 */
public class Manager {
    private Integer id;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
