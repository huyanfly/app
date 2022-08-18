package com.example.service.impl;

import com.example.bo.User;
import com.example.mapper.UserMapper;
import com.example.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 4096 kB 服务实现类
 * </p>
 *
 * @author Fly
 * @since 2022-08-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
