package com.chang.service;

import com.chang.entity.User;
import com.chang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息实现类
 *
 * @author Mr.Chang
 * @create 2018-07-25 15:15
 **/
@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.selectAll();
    }
}
