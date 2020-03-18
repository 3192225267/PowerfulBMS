package com.lzh.mapper;

import com.lzh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    @Select("SELECT * FROM USER WHERE number=#{number}")
    User Login(String number);

    @Select("SELECT * FROM USER")
    List<User> getAll();

    int getBackPwd(User u);

}