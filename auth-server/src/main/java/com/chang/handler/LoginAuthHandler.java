package com.chang.handler;

import com.chang.entity.User;
import com.chang.user.detail.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 执行自定义认证
 * @author Mr.Chang
 */
@Slf4j
@Component
public class LoginAuthHandler implements AuthenticationProvider {

    @Resource
    private CustomUserDetailService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("==========开始执行认证==========");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = (User) userDetailsService.loadUserByUsername(username);
        if (null == user){
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!user.isEnabled()){
            throw new DisabledException("账户被禁用");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

        if(!encoder.matches(password, user.getPassword())){
            throw new InvalidGrantException("用户名或密码错误");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
