package com.louis.teaSystemService.controller;

import com.louis.teaSystemService.pojo.ResultInfo;
import com.louis.teaSystemService.pojo.User;
import com.louis.teaSystemService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
        System.out.println("password:"+password);
        System.out.println("user ===> "+user);
        //�û�������
        if(user == null){
            return new ResultInfo(false,null,"�û�������");
        }else{
            if(user.getPassword().equals(password) == false){
                return new ResultInfo(false,null,"�������");
            }
        }
        session.setAttribute("user",user);
        ResultInfo resultInfo = new ResultInfo(true,1,"��¼�ɹ�");
        System.out.println("resultInfo ===> "+resultInfo);
        return resultInfo;
    }

}
