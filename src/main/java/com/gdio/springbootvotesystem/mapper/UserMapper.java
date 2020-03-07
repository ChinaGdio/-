package com.gdio.springbootvotesystem.mapper;

import com.gdio.springbootvotesystem.entities.Suggesstion;
import com.gdio.springbootvotesystem.entities.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author gdio
 * @create 2020-02-17 18:01
 */
public interface UserMapper {
    @Select("select id,user_name,password from tb_user where user_name=#{userName}")
    public User getUserByName(String userName);

    @Insert("insert into tb_user(user_name,password,email,gender) values(#{userName},#{password},#{email},#{gender})")
    public int insertUser(User user);

    @Select("select * from tb_user where user_name=#{userName} and password=#{password}")
    public List<User> findUserByName(User user);

    @Select("select * from tb_user where password=#{password}")
    public User findUserByMD5(String password);

    @Select("select id from tb_user where password=#{password}")
    public int findIdByMD5(String password);

    @Select("select user_name from tb_user where id=#{id}")
    public String getUserNameById(Integer id);

    @Insert("insert into tb_suggest values(#{id},#{userName},#{email},#{gender},#{suggest})")
    public int insertSuggest(Suggesstion suggesstion);

    @Select("select * from tb_user")
    public List<User> findAllUsers();

    @Select("select * from tb_user where id=#{id}")
    public User findUserById(Integer id);

    @Delete("delete from tb_user where id=#{id}")
    public int deleteUser(Integer id);

    @Update("update tb_user set user_name=#{userName},email=#{email},password=#{password} where id=#{id}")
    public int updateUser(User user);
}
