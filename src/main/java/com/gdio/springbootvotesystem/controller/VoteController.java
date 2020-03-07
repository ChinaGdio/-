package com.gdio.springbootvotesystem.controller;

import com.gdio.springbootvotesystem.entities.*;
import com.gdio.springbootvotesystem.mapper.*;
import com.gdio.springbootvotesystem.temp.TempData;
import com.gdio.springbootvotesystem.tools.MyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author gdio
 * @create 2020-02-18 12:32
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
public class VoteController {
    @Autowired
    VoteMapper voteMapper;
    @Autowired
    OptionMapper optionMapper;
    @Autowired
    UserCommentMapper userCommentMapper;
    @Autowired
    StatisticsMapper statisticsMapper;
    @Autowired
    AgreeableMapper agreeableMapper;
    @Autowired
    RecentlyBrowseMapper recentlyBrowseMapper;
    //向后翻页
    @RequestMapping("/changepage")
    @ResponseBody
    public Map<String,Object> changePage(){
        Map<String,Object> map=new HashMap<>();
        //当前页已经是最后一页
        System.out.println(TempData.PAGENOW);
        if(TempData.PAGENOW==(int)TempData.PAGECOUNT){
            map.put("msg","1");
            return map;
        }
        TempData.PAGENOW+=1;
        //当前是倒数第二页，即将进入最后一页
        if(TempData.PAGENOW==(int)TempData.PAGECOUNT){
            List<Vote> allVote=voteMapper.getVoteLimit((TempData.PAGENOW-1)*(int)TempData.EACHPAGE,(int)TempData.LASTPAGE);
            Collections.sort(allVote);
            map.put("allVote",allVote);
        }
        List<Vote> allVote=voteMapper.getVoteLimit((TempData.PAGENOW-1)*(int)TempData.EACHPAGE,(int)TempData.EACHPAGE);
        Collections.sort(allVote);
        map.put("allVote",allVote);
        return map;
    }
    //向前翻页
    @RequestMapping("/pageback")
    @ResponseBody
    public Map<String,Object> pageBack(){
        Map<String,Object> map=new HashMap<>();
        if(TempData.PAGENOW==1){
            map.put("msg","1");
            return map;
        }
        TempData.PAGENOW-=1;
        List<Vote> allVote=voteMapper.getVoteLimit((TempData.PAGENOW-1)*(int)TempData.EACHPAGE,(int)TempData.EACHPAGE);
        Collections.sort(allVote);
        map.put("allVote",allVote);
        return map;
    }

    @RequestMapping("toAgree")
    @ResponseBody
    public Map<String,Object> toAgree(Integer id){
        userCommentMapper.updateAgree();
        Map<String,Object> map=new HashMap<>();
        map.put("agree",userCommentMapper.getAgree(id));
        return map;
    }

    //数据可视化
    @RequestMapping("/showtable")
    @ResponseBody
    public List<Echart> showtable(Model model){
        Map<String,Object> map=new HashMap<>();
        Vote vote=voteMapper.findVoteById(TempData.getVid());
        vote.setOptions(optionMapper.findOptionByVid(vote.getId()));
        List<Echart> list=MyTool.xAxisdata(vote);
        System.out.println(list);
        return list;
    }
    @RequestMapping("toshowtable")
    public String toShowTable(Integer vid,Map<String,Object>map){
        TempData.setVid(vid);
        return "showDiagram";
    }

    //进入投票主题
    @GetMapping("toVotePage")
    public String toVotePage(Integer vid, Map<String,Object> map, HttpSession httpSession){
        //刷新投票的被点击次数
        voteMapper.updateCheckTimes(vid);
        //查询出此条投票记录
        Vote vote = voteMapper.findVoteById(vid);
        //保存用户浏览记录
        recentlyBrowseMapper.createRecentlyBrowse(new RecentlyBrowse(TempData.LOGINUSER.getId(),vid,vote.getVoteName(),new Date()));
        //查询出此条投票的选项信息
        List<Option> options=optionMapper.findOptionByVid(vid);
        vote.setOptions(MyTool.countAmount(options));
        //返回此投票的完整信息
        map.put("voteItem",vote);
        //返回属于此投票的评论
        List<UserComment> commentByVote = userCommentMapper.findCommentByVote(vid);
        List<RecentlyBrowse> allBrowse=recentlyBrowseMapper.getLimitRecentlyBrowseById(TempData.LOGINUSER.getId(),TempData.BROWSENUM);
        httpSession.setAttribute("allBrowse",allBrowse);
        map.put("comments",commentByVote);
        //判断当前用户是否参与过此投票,是返回1
        map.put("alreadyVote",statisticsMapper.findStatistics(new Statistics(TempData.LOGINUSER.getId(),vid)));
        return "votePage";
    }

    //发布投票
    @PostMapping("voteCommit")
    public String voteCommit(Vote vote, HttpServletRequest hp,Map<String,Object> map){
        vote.setId(MyTool.createId());
        String option1=hp.getParameter("kp");
        String[] options=option1.split(",");
        for(String str:options){
            Option option=new Option();
            option.setVid(vote.getId());
            option.setOptionName(str);
            optionMapper.insertOption(option);
        }
        vote.setPublisher(TempData.LOGINUSER.getUserName());

        voteMapper.insertVote(vote);
        //刷新一下总投票数
        TempData.VOTECOUNT+=1;
        //刷新一下总页数
        TempData.PAGECOUNT=(int)Math.ceil(TempData.VOTECOUNT/TempData.EACHPAGE);
        List<Vote> allVote=voteMapper.getVoteLimit(0,(int)TempData.EACHPAGE);
        Collections.sort(allVote);
        map.put("allVote",allVote);
        return "tables";
    }

    //进入投票列表
    @GetMapping("tables")
    public String toTables(Map<String,Object> map){
        if(TempData.VOTECOUNT==0){
            TempData.VOTECOUNT=voteMapper.getVoteCount();
            TempData.PAGECOUNT=(int)Math.ceil(TempData.VOTECOUNT/TempData.EACHPAGE);
            TempData.LASTPAGE=(int)(TempData.VOTECOUNT%TempData.EACHPAGE);
            System.out.println(TempData.LASTPAGE);
        }
        List<Vote> allVote=voteMapper.getVoteLimit(0,(int)TempData.EACHPAGE);
        Collections.sort(allVote);
        map.put("allVote",allVote);
        return "tables";
    }

    //提交投票
    @PostMapping("commitVote")
    public String commitVote(Integer vid,Map<String,Object> map,HttpServletRequest hp){
        //将选中的选项支持人数都+1
        String[] options=hp.getParameterValues("myVote");
        for(String str:options){
            optionMapper.updataSupportById(Integer.valueOf(str));
        }
        //将用户对此投票操作记录起来
        Statistics statistics=new Statistics(TempData.LOGINUSER.getId(),vid);
        System.out.println("保存的"+statistics);
        statisticsMapper.insertStatistics(statistics);
        //返回投票列表页面
        List<Vote> allVote=voteMapper.getVoteLimit(0,(int)TempData.EACHPAGE);
        map.put("allVote",allVote);
        return "tables";
    }

    //提交评论
    @PostMapping("commitComment")
    public String commitComment(UserComment userComment,Map<String,Object> map){
        //刷新回复数
        voteMapper.updateComments(userComment.getVid());
        //保存评论
        userComment.setUserName(TempData.LOGINUSER.getUserName());
        userCommentMapper.insertComment(userComment);
        //刷新此投票信息
        Vote vote = voteMapper.findVoteById(userComment.getVid());
        List<Option> options=optionMapper.findOptionByVid(vote.getId());
        vote.setOptions(MyTool.countAmount(options));
        map.put("voteItem",vote);
        map.put("comments",userCommentMapper.findCommentByVote(userComment.getVid()));
        map.put("alreadyVote",statisticsMapper.findStatistics(new Statistics(TempData.LOGINUSER.getId(),userComment.getVid())));
        return "votePage";
    }
}
