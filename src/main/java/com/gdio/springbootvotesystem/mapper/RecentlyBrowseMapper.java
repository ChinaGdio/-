package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.RecentlyBrowse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author gdio
 * @create 2020-02-28 19:08
 */
public interface RecentlyBrowseMapper {
    //插入用户浏览记录
    @Insert("insert into tb_recentlybrowse(user_id,vote_id,vote_name,browsedate) values(#{userId},#{voteId},#{voteName},#{ Browsedate})")
    public int createRecentlyBrowse(RecentlyBrowse recentlyBrowse);
    //获取一位用户的所有浏览记录
    @Select("select * from tb_recentlybrowse where user_id=#{userId}")
    public List<RecentlyBrowse> getAllRecentlyBrowseById(Integer userId);
    @Select(" select * from (select * from tb_recentlybrowse where user_id=#{userId}) aa order by browsedate desc limit #{count}")
    public List<RecentlyBrowse> getLimitRecentlyBrowseById(Integer userId,Integer count);
}
