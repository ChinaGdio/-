package com.gdio.springbootvotesystem.temp;

import com.gdio.springbootvotesystem.entities.Manager;
import com.gdio.springbootvotesystem.entities.User;

import java.net.UnknownServiceException;

/**
 * @author gdio
 * @create 2020-02-22 21:44
 */
public class TempData {
    public static User LOGINUSER;
    public static Integer PAGENOW=1;
    //每页的记录数量
    public static double EACHPAGE=5.0;
    //记录总数
    public static int VOTECOUNT=0;
    //页数
    public static int PAGECOUNT=0;
    //最后一页的记录数
    public static int LASTPAGE=0;
    //当前处于的投票
    private static int vid=1;
    //当前登录的管理员
    private static Manager manager=null;
    public static Integer BROWSENUM=5;

    public static Manager getManager() {
        return manager;
    }

    public static void setManager(Manager manager) {
        TempData.manager = manager;
    }

    public static int getVid() {
        return vid;
    }

    public static void setVid(int vid) {
        TempData.vid = vid;
    }

}
