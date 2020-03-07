package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.Statistics;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author gdio
 * @create 2020-02-22 18:55
 */
public interface StatisticsMapper {
    @Insert("insert into tb_statistics values(#{vid},#{uid})")
    public int insertStatistics(Statistics statistics);

    @Select("select count(*) from tb_statistics where vid=#{vid} and uid=#{uid}")
    public int findStatistics(Statistics statistics);
}
