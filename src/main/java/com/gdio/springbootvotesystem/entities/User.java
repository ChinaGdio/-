package com.gdio.springbootvotesystem.entities;

import com.gdio.springbootvotesystem.tools.MyTool;

import java.util.Date;
import java.util.List;

/**
 * @author gdio
 * @create 2020-02-17 14:14
 */
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private Integer gender;
    private List<RecentlyBrowse> recentlyBrowses;

    public List<RecentlyBrowse> getRecentlyBrowses() {
        return recentlyBrowses;
    }

    public void setRecentlyBrowses(List<RecentlyBrowse> recentlyBrowses) {
        this.recentlyBrowses = recentlyBrowses;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
