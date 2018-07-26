package com.chang.translator;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义AuthenticationEntryPoint返回实现类
 * @author Mr.Chang
 **/

public class AuthExceptionEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException)
            throws  ServletException {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", "401");
        map.put("message", authException.getMessage());
        map.put("data", null);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            response.getOutputStream().write(JSON.toJSONString(map).getBytes());
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
