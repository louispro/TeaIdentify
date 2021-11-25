package com.louis.teaSystemService.mapper;

import com.louis.teaSystemService.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int insertUser(User user);

    User getUserByUsername(String username);
}
