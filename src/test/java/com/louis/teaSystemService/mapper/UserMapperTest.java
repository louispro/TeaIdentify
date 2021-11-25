package com.louis.teaSystemService.mapper;

import com.louis.teaSystemService.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserByUsername() {
        User user = userMapper.getUserByUsername("123");
        System.out.println(user);
    }

    @Test
    public void insertUser() {
    }
}