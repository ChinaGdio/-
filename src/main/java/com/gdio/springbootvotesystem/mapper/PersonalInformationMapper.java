package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.PersonalInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author gdio
 * @create 2020-02-27 14:59
 */
public interface PersonalInformationMapper {
    @Insert("insert into tb_info values(#{id},#{company},#{userName},#{email},#{firstName},#{lastName},#{address},#{city},#{country},#{zipCode},#{personalSign})")
    public int insertPersonalInformation(PersonalInformation personalInformation);
    @Select("select * from tb_info where id=#{id}")
    public PersonalInformation findInfoById(Integer id);
    @Update("update tb_info set company=#{company},user_name=#{userName},email=#{email},firstName=#{firstName},lastName=#{lastName},address=#{address},city=#{city},country=#{country},zipCode=#{zipCode},personalSign=#{personalSign} where id=#{id}")
    public int updateInfo(PersonalInformation personalInformation);
}
