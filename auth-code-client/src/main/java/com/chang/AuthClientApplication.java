package com.chang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * oauth2客户端启动入口
 * @author Mr.Chang
 */
@SpringBootApplication
public class AuthClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthClientApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
