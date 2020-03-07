package com.gdio.springbootvotesystem.entities;

import com.gdio.springbootvotesystem.enums.CommunityEnum;
import com.gdio.springbootvotesystem.tools.MyTool;

import java.util.Date;
import java.util.List;

/**
 * @author gdio
 * @create 2020-02-17 23:56
 */
public class Vote implements Comparable<Vote> {
    private Integer id;
    private String voteName;
    private String discribe;
    private String optional;
    private String Publisher;
    private Integer checkTimes=0;
    private Integer commentsNum=0;
    private Date createDate=new Date();
    private CommunityEnum community=CommunityEnum.MAINCOMMUNITY;

    public CommunityEnum getCommunity() {
        return community;
    }

    public void setCommunity(CommunityEnum community) {
        this.community = community;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private List<Option> options;

    public Integer getCheckTimes() {
        return checkTimes;
    }

    public void setCheckTimes(Integer checkTimes) {
        this.checkTimes = checkTimes;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Vote() {

    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voteName='" + voteName + '\'' +
                ", discribe='" + discribe + '\'' +
                ", optional='" + optional + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", options=" + options +
                '}';
    }

    @Override
    public int compareTo(Vote o) {
        if(this.checkTimes>o.checkTimes){
            return -1;
        }
        if(this.checkTimes<o.checkTimes){
            return 1;
        }
        return 0;
    }
}
