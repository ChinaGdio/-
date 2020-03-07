package com.gdio.springbootvotesystem.entities;

import com.gdio.springbootvotesystem.tools.MyTool;

/**
 * @author gdio
 * @create 2020-02-20 17:52
 */
public class UserComment {
    Integer id;
    Integer vid;
    String userName;
    String content;
    Integer agree=0;
    Integer disagree=0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getDisagree() {
        return disagree;
    }

    public void setDisagree(Integer disagree) {
        this.disagree = disagree;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserComment() {
        this.id= MyTool.createId();
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "id=" + id +
                ", vid=" + vid +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", agree=" + agree +
                ", disagree=" + disagree +
                '}';
    }
}
