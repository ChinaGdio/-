package com.gdio.springbootvotesystem.entities;

/**
 * @author gdio
 * @create 2020-02-25 13:45
 */
public class Agreeable {
    Integer uid;
    Integer cid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Agreeable(Integer uid, Integer cid) {
        this.uid = uid;
        this.cid = cid;
    }
}
