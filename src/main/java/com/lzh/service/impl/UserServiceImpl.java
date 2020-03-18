package com.lzh.service.impl;


import com.lzh.mapper.UserMapper;
import com.lzh.pojo.User;
import com.lzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-02-29 18:55:23
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
   private UserMapper userMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User Login(String username,String password) {
        User user=userMapper.Login(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                user.setStart(1);
                return user;
            }else{
                user.setStart(0);
                return user;
            }
        }
            return user;

    }

    @Override
    public Boolean verification(String number) {
        User user=userMapper.Login(number);
        if (user!=null){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public int getBackPwd(User u) {
        return userMapper.getBackPwd(u);
    }

    @Override
    public User getNumber(String number) {
        return userMapper.Login(number);
    }
}