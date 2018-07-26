package com.chang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务启动入口
 * @author Mr.Chang
 */
@MapperScan(basePackages = {"com.chang.mapper"})
@SpringBootApplication
public class AuthResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthResourceApplication.class, args);
    }
}
