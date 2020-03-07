package com.gdio.springbootvotesystem.entities;

import java.util.Date;

/**
 * @author gdio
 * @create 2020-02-22 17:05
 */
public class Suggesstion {
    private Integer id;
    private String userName;
    private String email;
    private Integer gender;
    private String suggest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Suggesstion() {
        Date date=new Date();
        this.id=(int)date.getTime();
    }
}
