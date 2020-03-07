package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.Vote;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Date;
import java.util.List;

/**
 * @author gdio
 * @create 2020-02-18 12:23
 */
public interface VoteMapper {
    @Select("select id,vote_name from tb_vote")
    public List<Vote> findAllVote();

    @Select("select * from tb_vote where id=#{id}")
    public Vote findVoteById(Integer id);

    @Insert("insert into tb_vote values(#{id},#{voteName},#{discribe},#{optional},#{publisher},#{checkTimes},#{commentsNum},#{createDate})")
    public int insertVote(Vote vote);

    @Select("select count(*) from tb_vote")
    public int getVoteCount();

    @Select("select id,vote_name,checkTimes,commentsNum,createDate from tb_vote limit #{start},#{pageNum}")
    public List<Vote> getVoteLimit(int start,int pageNum);

    @Update("update tb_vote set vote_name=#{voteName},discribe=#{discribe},optional=#{optional},Publisher=#{Publisher} where id=#{id}")
    public int updateVote(Vote vote);

    @Delete("delete from tb_vote where id=#{id}")
    public int deleteVote(Integer id);

    //点击进入此投票后，访问数+1
    @Update("update tb_vote set checkTimes=checkTimes+1 where id=#{id}")
    public int updateCheckTimes(Integer id);

    //点击进入此投票后，访问数+1
    @Update("update tb_vote set commentsNum=commentsNum+1 where id=#{id}")
    public int updateComments(Integer id);


}
