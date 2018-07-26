package com.chang.controller;

import com.chang.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author Mr.Chang
 * @create 2018-07-24 10:00
 **/
@RequestMapping("api")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("users")
    public Object getUsers(){
        return userService.getUserList();
    }
}
