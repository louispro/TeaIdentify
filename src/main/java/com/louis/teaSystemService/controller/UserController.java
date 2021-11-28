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
    public ResultInfo login(User user, HttpSession session) throws UnsupportedEncodingException {
        User user1 = userService.getUserByUsername(user.getUsername());

        if(user1 == null){
            return new ResultInfo(false,null,"用户名不存在");
        }else{
            //获取加密之后的密码
            String password = UserUtils.md5(user.getPassword(),user1.getId());
            if(user1.getPassword().equals(password) == false){
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
        //设置id和密码加密
        user = UserUtils.dealUser(user);
        userService.insertUser(user);
        return new ResultInfo(true,null,"注册成功");
    }

}
