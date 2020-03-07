package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.Agreeable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author gdio
 * @create 2020-02-25 13:46
 */
public interface AgreeableMapper {
    @Select("select count(*) from tb_agree where uid=#{uid} and cid=#{cid}")
    public int getAgreeRecordById(Agreeable agreeable);

    @Insert("insert into tb_agree values(#{uid},#{cid})")
    public int insertAgree(Agreeable agreeable);
}
