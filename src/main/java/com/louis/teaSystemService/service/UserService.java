package com.louis.teaSystemService.service;

import com.louis.teaSystemService.pojo.User;

public interface UserService {

    int insertUser(User user);

    User getUserByUsername(String username);
}
