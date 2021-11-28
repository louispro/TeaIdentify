package com.louis.teaSystemService.util;

import com.louis.teaSystemService.pojo.User;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * ��С�D
 * www.louis.com
 */
public class UserUtils {

    //对用户数据进行处理
    public static User dealUser(User user) throws UnsupportedEncodingException {
        //唯一id
        user.setId(UUID.randomUUID().toString());
        //加密
        String password = UserUtils.md5(user.getPassword(),user.getId());
        user.setPassword(password);
        return user;
    }

    //加密
    public static String md5(String password,String id){
        StringBuilder pw = new StringBuilder(password);
        pw.append(id);
        return DigestUtils.md5DigestAsHex(pw.toString().getBytes());
    }




    public static void main(String[] args) throws UnsupportedEncodingException {
    }
}
