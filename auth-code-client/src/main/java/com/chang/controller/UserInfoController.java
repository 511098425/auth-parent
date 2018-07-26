package com.chang.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mr.Chang
 * @create 2018-07-25 17:29
 **/
@Slf4j
@RestController
public class UserInfoController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * authorization_code获取数据
     * @param authentication
     * @return
     */
    @GetMapping("/user")
    public Object getUser(Authentication authentication) {
        log.info("auth : {}", JSON.toJSONString(authentication));
        log.info("认证结果：{}",JSON.toJSONString(authentication.getDetails()));
        JSONObject obj = JSON.parseObject(JSON.toJSONString(authentication.getDetails()));
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:7070/api/users?access_token="+obj.get("tokenValue").toString(),String.class);
        return responseEntity.getBody();
    }

}
