package com.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.bo.User;
import com.example.service.IUserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Resource
    private PasswordEncoder pe;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、查询数据库判断用户名是否存在,如果不存在就会抛出UsernameNotFoundException异常
        User user = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUserName, username)
                .last("limit 1"));
        if(ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        String password = pe.encode(user.getPassWord());
        //用户名,密码,权限列表
        return new org.springframework.security.core.userdetails.User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList(
                        "admin,normal,ROLE_zhangSan,/main.html"
        ));
    }
}
