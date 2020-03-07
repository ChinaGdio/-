package com.gdio.springbootvotesystem.entities;


/**
 * @author gdio
 * @create 2020-02-18 12:28
 */
public class Statistics {
    private Integer uid;
    private Integer vid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Statistics() {
    }

    public Statistics(Integer uid, Integer vid) {
        this.uid = uid;
        this.vid = vid;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "uid=" + uid +
                ", vid=" + vid +
                '}';
    }
}
