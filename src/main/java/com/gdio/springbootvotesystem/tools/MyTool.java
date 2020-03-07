package com.gdio.springbootvotesystem.tools;

import com.gdio.springbootvotesystem.entities.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gdio
 * @create 2020-02-17 23:09
 */
public class MyTool {
    public static String FormatData(double d1){
        d1*=100;
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        String format = decimalFormat.format(d1)+"%";
        return format;
    }
    public static boolean checkUser(User user){
        if(user.getUserName()==null||"".equals(user.getUserName())){
            return false;
        }
        if(user.getEmail()==null||"".equals(user.getEmail())){
            return false;
        }
        if(user.getPassword()==null||"".equals(user.getPassword())){
            return false;
        }
        return true;
    }
    public static List<Option> countAmount(List<Option> options){
        double sum=0;
        for(Option option:options){
            sum+=option.getSupport();
        }
        for(Option option:options){
            option.setPercent(FormatData(option.getSupport()/sum));
        }
        return options;
    }
    public static int createId(){
        return Math.abs((int)(new Date().getTime()));
    }
    public static List<Echart> xAxisdata(Vote vote){
        String[] str=new String[vote.getOptions().size()];
        List<Echart> arr=new ArrayList<>();
        for(int i=0;i<vote.getOptions().size();i++){
            arr.add(new Echart(vote.getOptions().get(i).getOptionName(),vote.getOptions().get(i).getSupport()));
        }
        return arr;
    }
    public static boolean isNullCheck(String str){
        return str==null||str=="";
    }
    public static Vote checkVote(Vote voteA,Vote voteB){
        if(isNullCheck(voteB.getVoteName())){
            voteB.setVoteName(voteA.getVoteName());
        }
        if (isNullCheck(voteB.getDiscribe())){
            voteB.setDiscribe(voteA.getDiscribe());
        }
        if (isNullCheck(voteB.getOptional())){
            voteB.setOptional(voteA.getOptional());
        }
        if (isNullCheck(voteB.getPublisher())){
            voteB.setDiscribe(voteA.getPublisher());
        }
        return voteB;
    }
    public static User checkUser(User userA,User userB){
        if(isNullCheck(userB.getUserName())){
            userB.setUserName(userA.getUserName());
        }
        if(isNullCheck(userB.getPassword())){
           userB.setEmail(userA.getEmail());
        }
        if(isNullCheck(userB.getPassword())){
            userB.setPassword(userA.getPassword());
        }
        return userB;
    }
    public static PersonalInformation createInfo(User user){
        PersonalInformation p=new PersonalInformation();
        p.setUserName(user.getUserName());
        p.setEamil(user.getEmail());
        return p;
    }
    public static String getMD5(String plainText, int length) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");//获取MD5实例
            md.update(plainText.getBytes());//此处传入要加密的byte类型值
            byte[] digest = md.digest();//此处得到的是md5加密后的byte类型值

            /*
               下边的运算就是自己添加的一些二次小加密，记住这个千万不能弄错乱，
                   否则在解密的时候，你会发现值不对的（举例：在注册的时候加密方式是一种，
                在我们登录的时候是不是还需要加密它的密码然后和数据库的进行比对，但是
            最后我们发现，明明密码对啊，就是打不到预期效果，这时候你就要想一下，你是否
             有改动前后的加密方式）
            */
            int i;
            StringBuilder sb = new StringBuilder();
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    sb.append(0);
                sb.append(Integer.toHexString(i));//通过Integer.toHexString方法把值变为16进制
            }
            return sb.toString().substring(0, length);//从下标0开始，length目的是截取多少长度的值
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
