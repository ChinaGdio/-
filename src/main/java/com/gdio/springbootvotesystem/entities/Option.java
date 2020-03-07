package com.gdio.springbootvotesystem.entities;


import com.gdio.springbootvotesystem.tools.MyTool;

/**
 * @author gdio
 * @create 2020-02-17 23:59
 */
public class Option {
    //自己的id
    private Integer id;
    //所属vote的id
    private Integer vid;
    //选项内容
    private String optionName;
    //支持人数
    private Integer support=0;
    //支持率
    private String percent="0%";

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getSupport() {
        return support;
    }

    public void setSupport(Integer support) {
        this.support = support;
    }

    public Option() {
        this.id= MyTool.createId();
    }
}
