package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.Option;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author gdio
 * @create 2020-02-18 12:24
 */
public interface OptionMapper {
    @Select("select * from tb_option where vid=#{vid}")
    public List<Option> findOptionByVid(Integer vid);

    @Insert("insert into tb_option values(#{id},#{vid},#{optionName},#{support})")
    public int insertOption(Option option);

    @Update("update tb_option set support=support+1 where id=#{id}")
    public int updataSupportById(Integer id);

    @Update("update tb_option set support=support+1 where option_name=#{optionName}")
    public int updataSupportByName(String optionName);

    @Update("update tb_option set option_name=#{optionName} where vid=#{vid}")
    public int updateOption(Integer vid,String optionName);

}
