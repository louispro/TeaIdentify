package com.louis.teaSystemService.service.impl;

import com.louis.teaSystemService.mapper.UserMapper;
import com.louis.teaSystemService.pojo.User;
import com.louis.teaSystemService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 赖小燚
 * @www.louis_lai.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
