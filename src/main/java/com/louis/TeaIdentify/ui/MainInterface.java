package com.louis.TeaIdentify.ui;

import com.louis.TeaIdentify.Component.BackGroupPanel;
import com.louis.TeaIdentify.util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class MainInterface {

    JFrame jf = new JFrame("茶叶嫩芽识别系统");
    final int WIDTH = 500;
    final int HEIGHT = 300;

    //组装视图
    public void init() throws IOException {
        //设置窗口相关属性
        jf.setIconImage(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/teaIcon.jpg")));
        jf.setBounds((ScreenUtil.getScreenWidth()-WIDTH)/2,(ScreenUtil.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        //设置窗口内容
        BackGroupPanel bgPanel = new BackGroupPanel(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/loginBg.png")));

        //font字体
        Font font = new Font("楷体",Font.BOLD,16);

        //组装用户名
        Box userBox = Box.createHorizontalBox();
        JLabel userLabel = new JLabel("用户名：");
        userLabel.setFont(font);
        JTextField userField = new JTextField(15);
        userBox.add(userLabel);
        userBox.add(Box.createHorizontalStrut(10));
        userBox.add(userField);

        //组装密码
        Box pwBox = Box.createHorizontalBox();
        JLabel pwLabel = new JLabel("密  码：");
        pwLabel.setFont(font);
        JTextField pwField = new JTextField(15);
        pwBox.add(pwLabel);
        pwBox.add(Box.createHorizontalStrut(10));
        pwBox.add(pwField);

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        JButton registerBtn = new JButton("注册");
        loginBtn.setFont(font);
        registerBtn.setFont(font);
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(registerBtn);


        //组装登录相关的内容
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
    }

    //客户端程序的入口
    public static void main(String[] args) {
        try {
            new MainInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
