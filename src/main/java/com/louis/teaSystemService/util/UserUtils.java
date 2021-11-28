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
        //Ψһid
        user.setId(UUID.randomUUID().toString());
        //����
        StringBuilder pw = new StringBuilder(user.getPassword());
        pw.append(user.getPhone()+user.getSex());
        user.setPassword(DigestUtils.md5DigestAsHex(pw.toString().getBytes()));
        return user;
    }




    public static void main(String[] args) throws UnsupportedEncodingException {
    }
}
