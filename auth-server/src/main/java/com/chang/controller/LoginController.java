package com.chang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录页面
 *
 * @author Mr.Chang
 * @create 2018-07-27 11:32
 **/
@Controller
public class LoginController {

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
