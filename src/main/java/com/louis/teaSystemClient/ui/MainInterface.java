package com.louis.teaSystemClient.ui;


import com.louis.teaSystemClient.Component.BackgroundPanel;
import com.louis.teaSystemClient.util.JsonUtils;
import com.louis.teaSystemClient.util.PostUtils;
import com.louis.teaSystemClient.util.ScreenUtils;
import com.louis.teaSystemClient.pojo.ResultInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.awt.SystemColor.info;

/**
 * @author 赖小燚
 * @www.louis_lai.com
 */
public class MainInterface {

    JFrame jf = new JFrame("茶叶嫩芽识别系统");
    final int WIDTH = 500;
    final int HEIGHT = 300;

    //初始化登陆界面
    public void init() throws IOException {
        //设置组件图标
        jf.setIconImage(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/teaIcon.jpg")));
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/loginBg.png")));

        //font字体
        Font font = new Font("楷体",Font.BOLD,16);

        //用户box
        Box userBox = Box.createHorizontalBox();
        JLabel userLabel = new JLabel("用户：");
        userLabel.setFont(font);
        JTextField userField = new JTextField(15);
        userBox.add(userLabel);
        userBox.add(Box.createHorizontalStrut(10));
        userBox.add(userField);

        //密码box
        Box pwBox = Box.createHorizontalBox();
        JLabel pwLabel = new JLabel("密码：");
        pwLabel.setFont(font);
        JTextField pwField = new JTextField(15);
        pwBox.add(pwLabel);
        pwBox.add(Box.createHorizontalStrut(10));
        pwBox.add(pwField);

        //按钮box
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        JButton registerBtn = new JButton("注册");
        loginBtn.setFont(font);
        registerBtn.setFont(font);
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(registerBtn);


        //组装登陆界面
        Box loginBox = Box.createVerticalBox();
        loginBox.add(Box.createVerticalStrut(60));
        loginBox.add(userBox);
        loginBox.add(Box.createVerticalStrut(30));
        loginBox.add(pwBox);
        loginBox.add(Box.createVerticalStrut(30));
        loginBox.add(btnBox);

        bgPanel.add(loginBox);
        jf.add(bgPanel);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setResizable(false);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户名和密码
                String username = userField.getText();
                String password = pwField.getText();
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                //发送请求
                PostUtils.postWithParams("http://localhost:8080/user/login",params,result -> {
                    ResultInfo info = JsonUtils.parseResult(result);
                    System.out.println("info:"+info);
                    //请求成功
                    if(info.isFlag()){
                        try{
                            JOptionPane.showMessageDialog(jf,info.getMessage());
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(jf,info.getMessage());
                    }
                },/**请求失败**/()-> JOptionPane.showMessageDialog(jf,"网络异常，请稍后重试"));
            }
        });
    }

    //主窗口
    public static void main(String[] args) {
        try {
            new MainInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
