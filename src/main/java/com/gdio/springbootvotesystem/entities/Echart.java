package com.gdio.springbootvotesystem.entities;

/**
 * @author gdio
 * @create 2020-02-25 23:05
 */
public class Echart {
    String name;
    Integer num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Echart(String name, Integer num) {
        this.name = name;
        this.num = num;
    }
}
