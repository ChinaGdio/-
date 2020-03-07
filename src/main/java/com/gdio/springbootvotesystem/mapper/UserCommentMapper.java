package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.UserComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author gdio
 * @create 2020-02-20 17:55
 */
public interface UserCommentMapper {
    @Select("select * from tb_comments where vid=#{vid}")
    public List<UserComment> findCommentByVote(Integer vid);

    @Insert("insert into tb_comments values(#{id},#{vid},#{userName},#{content},#{agree},#{disagree})")
    public int insertComment(UserComment userComment);

    @Update("update tb_comments set agree=agree+1 where id=#{id}")
    public int updateAgree();

    @Select("select agree from tb_comments where id=#{id}")
    public int getAgree(Integer id);
}
