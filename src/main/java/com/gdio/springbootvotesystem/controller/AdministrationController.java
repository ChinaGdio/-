package com.gdio.springbootvotesystem.controller;

import com.gdio.springbootvotesystem.entities.Manager;
import com.gdio.springbootvotesystem.entities.Suggesstion;
import com.gdio.springbootvotesystem.entities.User;
import com.gdio.springbootvotesystem.entities.Vote;
import com.gdio.springbootvotesystem.mapper.AdministrationMapper;
import com.gdio.springbootvotesystem.mapper.OptionMapper;
import com.gdio.springbootvotesystem.mapper.UserMapper;
import com.gdio.springbootvotesystem.mapper.VoteMapper;
import com.gdio.springbootvotesystem.temp.TempData;
import com.gdio.springbootvotesystem.tools.MyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author gdio
 * @create 2020-02-22 12:29
 */
@SuppressWarnings("ALL")
@Controller
public class AdministrationController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdministrationMapper administrationMapper;
    @Autowired
    VoteMapper voteMapper;
    @Autowired
    OptionMapper optionMapper;
    @GetMapping("contact")
    public String contact(){
        return "contactUs";
    }
    @PostMapping("toSuggest")
    public String commitSuggest(Suggesstion suggesstion){
        userMapper.insertSuggest(suggesstion);
        return "redirect:/index.html";
    }
    @PostMapping("managerLogin")
    public String managerLogin(Manager manager, Map<String,Object>map){
        int result=administrationMapper.findManagerByAccout(manager);
        if(result==1){
            TempData.setManager(manager);
            return "mainManager";
        }
        map.put("msg","错误的账号信息");
        return "redirect:/signup.html";
    }
    @GetMapping("toDeleteVote")
    public String toDeleteVote(Map<String,Object> map){
        List<Vote> allVote=voteMapper.getVoteLimit((TempData.PAGENOW-1)*(int)TempData.EACHPAGE,(int)TempData.EACHPAGE);
        map.put("allVote",allVote);
        map.put("dp","1");
        return "tables";
    }
    //进入投票列表(修改)
    @GetMapping("toEditVote")
    public String toEditVote(Map<String,Object> map){
        List<Vote> allVote=voteMapper.getVoteLimit((TempData.PAGENOW-1)*(int)TempData.EACHPAGE,(int)TempData.EACHPAGE);
        map.put("allVote",allVote);
        map.put("dp","2");
        return "tables";
    }
    //进入指定投票的修改页面
    @RequestMapping("/editVote")
    public String editvote(Integer id,Map<String,Object>map){
        System.out.println(id);
        Vote vote=voteMapper.findVoteById(id);
        System.out.println(vote);
        System.out.println(optionMapper.findOptionByVid(vote.getId()).size());
        vote.setOptions(optionMapper.findOptionByVid(vote.getId()));
        map.put("editvote",vote);
        return "voteEdit";
    }
    @PostMapping("commitedit")
    public String commitEdit(Vote vote, Map<String,Object>map, HttpServletRequest hp){
        System.out.println(vote);
        String[]options=hp.getParameterValues("ops");
        System.out.println(options);
        for(String option:options){
            if(option!=null&&option!=""){
                optionMapper.updateOption(vote.getId(),option);
            }
        }
        Vote v1=voteMapper.findVoteById(vote.getId());
        System.out.println(v1);
        voteMapper.updateVote(MyTool.checkVote(v1,vote));
        List<Vote> allVote=voteMapper.getVoteLimit((TempData.PAGENOW-1)*(int)TempData.EACHPAGE,(int)TempData.EACHPAGE);
        map.put("allVote",allVote);
        map.put("dp","2");
        return "tables";
    }
    @RequestMapping("deleteVote")
    public String toDeleteVote(Integer id,Map<String,Object>map){
        voteMapper.deleteVote(id);
        List<Vote> allVote=voteMapper.getVoteLimit((TempData.PAGENOW-1)*(int)TempData.EACHPAGE,(int)TempData.EACHPAGE);
        map.put("allVote",allVote);
        map.put("dp","2");
        return "tables";
    }
    @RequestMapping("toAddVote")
    public String toAddVote(Map<String,Object> map){
        map.put("dp","3");
        return "createVote";
    }
    @RequestMapping("toAddUser")
    public String toAddUser() {
        return "register";
    }

    @RequestMapping("toEditUser")
    public String toEditUser(Map<String,Object> map){
        map.put("allUser",userMapper.findAllUsers());
        map.put("dp","2");
        return "userstables";
    }
    @RequestMapping("editUser")
    public String toEditUser(Integer id,Map<String,Object> map){
        map.put("user",userMapper.findUserById(id));
        return "editUsers";
    }

    @RequestMapping("editusercommit")
    public String edituser(User user,Map<String,Object> map){
        User userA=userMapper.findUserById(user.getId());
        userMapper.updateUser(MyTool.checkUser(userA,user));
        map.put("allUser",userMapper.findAllUsers());
        map.put("dp","2");
        return "userstables";
    }

    @RequestMapping("toDeleteUser")
    public String toDeleteUser(Map<String,Object> map){
        map.put("allUser",userMapper.findAllUsers());
        map.put("dp","1");
        return "userstables";
    }
    @RequestMapping("deleteUser")
    public String deleteUser(Integer id,Map<String,Object> map){
        userMapper.deleteUser(id);
        map.put("allUser",userMapper.findAllUsers());
        map.put("dp","1");
        return "userstables";
    }

    @RequestMapping("checkMessage")
    public String checkMessage(Map<String,Object> map){
        List<Suggesstion> suggestionSimple = administrationMapper.getSuggestionSimple();
        map.put("allSuggest",suggestionSimple);
        return "suggeststables";
    }
    @RequestMapping("deleteSuggest")
    public String deleteSuggest(Integer id,Map<String,Object> map){
        administrationMapper.deleteSuggestion(id);
        List<Suggesstion> suggestionSimple = administrationMapper.getSuggestionSimple();
        map.put("allSuggest",suggestionSimple);
        return "suggeststables";
    }
    @RequestMapping("checksuggest")
    public String checksuggest(Integer id,Map<String,Object> map){
        Suggesstion suggesstion=administrationMapper.getSuggestion(id);
        map.put("suggest1",suggesstion);
        return "suggest";
    }
    @RequestMapping("exit")
    public String exit(){
        TempData.setManager(null);
        return "index";
    }
}
