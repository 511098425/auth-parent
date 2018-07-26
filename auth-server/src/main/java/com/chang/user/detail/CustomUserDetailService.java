package com.chang.user.detail;

import com.chang.entity.GrantAuthority;
import com.chang.entity.User;
import com.chang.exception.CustomOauthException;
import com.chang.mapper.GrantAuthorityMapper;
import com.chang.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class CustomUserDetailService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GrantAuthorityMapper grantAuthorityMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = new User();
        u.setUsername(s);
        u.setFrozen((byte)1);
        User user = userMapper.selectOne(u);
        log.info("用户名查询结果：{}", user);
        if(StringUtils.isEmpty(user)){
            throw new CustomOauthException("用户不存在");
        }
        //查询权限
        GrantAuthority ga = new GrantAuthority();
        ga.setUsername(s);
        List<GrantAuthority> authorityList = grantAuthorityMapper.select(ga);
        log.info("查询当前用户返回权限：{}", authorityList);
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (GrantAuthority gay: authorityList) {
            authorities.add(new SimpleGrantedAuthority(gay.getAuthority()));
        }
        return new User(user.getId(), user.getUsername(), user.getPassword(), user.getFrozen(), user.getPhone(), user.getEmail(), authorities);
    }
}
