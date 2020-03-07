package com.gdio.springbootvotesystem.controller;

import com.gdio.springbootvotesystem.entities.PersonalInformation;
import com.gdio.springbootvotesystem.entities.RecentlyBrowse;
import com.gdio.springbootvotesystem.entities.User;
import com.gdio.springbootvotesystem.entities.Vote;
import com.gdio.springbootvotesystem.mapper.PersonalInformationMapper;
import com.gdio.springbootvotesystem.mapper.RecentlyBrowseMapper;
import com.gdio.springbootvotesystem.mapper.UserMapper;
import com.gdio.springbootvotesystem.mapper.VoteMapper;
import com.gdio.springbootvotesystem.temp.TempData;
import com.gdio.springbootvotesystem.tools.MyTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author gdio
 * @create 2020-02-17 18:15
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    VoteMapper voteMapper;
    @Autowired
    PersonalInformationMapper personalInformationMapper;
    @Autowired
    RecentlyBrowseMapper recentlyBrowseMapper;

    //首页映射
    @RequestMapping({"/","index.html"})
    public String index(){
        return "index";
    }
    //进入注册页面
    @GetMapping("toRegister")
    public String toRegister(){
        return "register";
    }
    //提交注册信息
    @PostMapping("register")
    public String register(User user, Map<String,Object>map){
        String pwd=MyTool.getMD5(user.getUserName()+user.getPassword(),16);
        //用户提交的信息时用户名，密码，邮箱，性别
        if(MyTool.checkUser(user)){
            //在数据库中保存的密码是通过用户名和密码加密得出的
            user.setPassword(pwd);
            System.out.println(user);
            //将处理完的用户插入到数据库中
            userMapper.insertUser(user);
            //创建用户详细信息记录
            PersonalInformation p=MyTool.createInfo(user);
            System.out.println(userMapper.findIdByMD5(pwd));
            p.setId(userMapper.findIdByMD5(pwd));
            System.out.println(p);
            personalInformationMapper.insertPersonalInformation(p);
            map.put("registerUser",user);
            return "login";
        }
        map.put("msg","错误的注册信息，请再次检查");
        return "redirect:register";
    }
    //提交登陆信息进行登陆
    @PostMapping("votelist")
    public String login(User user, Map<String,Object>map, HttpSession httpSession){
        //用户登录信息包括用户名和密码
        User loginUser=userMapper.findUserByMD5(MyTool.getMD5(user.getUserName()+user.getPassword(),16));
        if(loginUser!=null){
            httpSession.setAttribute("loginUser",loginUser);
            httpSession.setAttribute("pinfo",personalInformationMapper.findInfoById(loginUser.getId()));
            TempData.LOGINUSER=loginUser;
            List<RecentlyBrowse> allBrowse=recentlyBrowseMapper.getLimitRecentlyBrowseById(loginUser.getId(),TempData.BROWSENUM);
            httpSession.setAttribute("allBrowse",allBrowse);
            return "redirect:/user.html";
        }
        map.put("msg","错误的登陆信息，请再次检查");
        return "login";
    }
    @RequestMapping("completeInformation")
    public String updateInfo(PersonalInformation personalInformation,HttpSession httpSession){
        personalInformation.setId(TempData.LOGINUSER.getId());
        personalInformationMapper.updateInfo(personalInformation);
        httpSession.setAttribute("pinfo",personalInformationMapper.findInfoById(TempData.LOGINUSER.getId()));
        return "redirect:/user.html";
    }

}
