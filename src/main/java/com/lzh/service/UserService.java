package com.lzh.service;


import com.lzh.pojo.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author zry
 * @since 2020-02-29 18:55:23
 */
public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
   // 登录
   User Login(String username, String password);
   //注册验证，如果填写的用户名不为空，则已被注册，返回flase，反之返回true
    Boolean verification(String number);

    List<User> getAll();
//    找回密码__修改密码
    int getBackPwd(User u);

    User getNumber(String number);


}