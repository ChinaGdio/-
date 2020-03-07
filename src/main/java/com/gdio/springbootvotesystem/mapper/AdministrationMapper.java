package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.Manager;
import com.gdio.springbootvotesystem.entities.Suggesstion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author gdio
 * @create 2020-02-22 17:36
 */
public interface AdministrationMapper {
    @Select("select count(*) from tb_manager where id=#{id} and password=#{password}")
    public int findManagerByAccout(Manager manager);

    @Select("select id,suggest from tb_suggest")
    public List<Suggesstion> getSuggestionSimple();

    @Select("select * from tb_suggest where id=#{id}")
    public Suggesstion getSuggestion(Integer id);

    @Delete("delete from tb_suggest where id=#{id}")
    public int deleteSuggestion(Integer id);

}
