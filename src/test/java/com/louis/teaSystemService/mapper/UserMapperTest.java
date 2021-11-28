package com.louis.teaSystemService.mapper;

import com.louis.teaSystemService.pojo.User;
import com.louis.teaSystemService.util.UserUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;

@ContextConfiguration(locations = "classpath:spring/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserByUsername() {
        User user = userMapper.getUserByUsername("louis");
        System.out.println(user);
    }

    @Test
    public void insertUser() throws UnsupportedEncodingException {
        User user = new User();
        user.setId("110");
        user.setUsername("satomi");
        user.setPassword("123456");
        user.setSex("女");
        user.setPhone("10010");
        user = UserUtils.dealUser(user);
        userMapper.insertUser(user);
    }
}