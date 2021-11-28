package com.louis.teaSystemService.controller;

import com.louis.teaSystemService.util.UserUtils;
import com.louis.teaSystemService.pojo.ResultInfo;
import com.louis.teaSystemService.pojo.User;
import com.louis.teaSystemService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * ��С�D
 * www.louis.com
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session){
        User user = userService.getUserByUsername(username);

        if(user == null){
            return new ResultInfo(false,null,"用户名不存在");
        }else{
            if(user.getPassword().equals(password) == false){
                return new ResultInfo(false,null,"密码错误");
            }
        }
        session.setAttribute("user",user);
        ResultInfo resultInfo = new ResultInfo(true,1,"登录成功");
        return resultInfo;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResultInfo register(User user,HttpSession session) throws UnsupportedEncodingException {
        User user1 = userService.getUserByUsername(user.getUsername());
        if(user1 != null){
            //用户名已存在
            return new ResultInfo(false,null,"用户名已存在");
        }
        user = UserUtils.dealUser(user);
        userService.insertUser(user);
        return new ResultInfo(true,null,"注册成功");
    }

}
