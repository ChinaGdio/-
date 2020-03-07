package com.gdio.springbootvotesystem.entities;

import java.util.Date;

/**
 * @author gdio
 * @create 2020-02-28 17:49
 */
//记录用户浏览记录的实体类
public class RecentlyBrowse implements Comparable<RecentlyBrowse> {
    private Integer id;
    //用户ID
    private Integer userId;
    //投票ID
    private Integer voteId;
    private String voteName;
    //浏览时间
    private Date Browsedate;

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public Date getBrowsedate() {
        return Browsedate;
    }

    public void setBrowsedate(Date browsedate) {
        Browsedate = browsedate;
    }

    @Override
    public int compareTo(RecentlyBrowse o) {
        if(this.getBrowsedate().before(o.getBrowsedate())){
            return -1;
        }
        if(this.getBrowsedate().after(o.getBrowsedate())){
            return 1;
        }
        return 0;
    }

    public RecentlyBrowse() {
    }

    public RecentlyBrowse(Integer userId, Integer voteId, String voteName, Date browsedate) {
        this.userId = userId;
        this.voteId = voteId;
        this.voteName = voteName;
        Browsedate = browsedate;
    }
}
