package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class RegisterPanel {

    JFrame jf = new JFrame("注册");

    final int WIDTH = 500;
    final int HEIGHT = 400;



    public void init() throws IOException {
        BackgroundPanel backGroupPanel = new BackgroundPanel(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/loginBg.png")));

        //font字体
        Font font = new Font("楷体",Font.BOLD,16);

        //登录box
        Box registerBox = Box.createVerticalBox();

        //用户box
        Box userBox = Box.createHorizontalBox();
        JLabel userLabel = new JLabel("用 户 名：");
        userLabel.setFont(font);
        JTextField userField = new JTextField(15);
        userBox.add(userLabel);
        userBox.add(Box.createHorizontalStrut(10));
        userBox.add(userField);

        //密码box
        Box pwBox = Box.createHorizontalBox();
        JLabel pwLabel = new JLabel("密    码：");
        pwLabel.setFont(font);
        JTextField pwField = new JTextField(15);
        pwBox.add(pwLabel);
        pwBox.add(Box.createHorizontalStrut(10));
        pwBox.add(pwField);

        //确认密码box
        Box rePwBox = Box.createHorizontalBox();
        JLabel rePwLabel = new JLabel("确认密码：");
        rePwLabel.setFont(font);
        JTextField rePwField = new JTextField(15);
        rePwBox.add(rePwLabel);
        rePwBox.add(Box.createHorizontalStrut(10));
        rePwBox.add(rePwField);

        //手机号box
        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLabel = new JLabel("手 机 号：");
        phoneLabel.setFont(font);
        JTextField phoneField = new JTextField(15);
        phoneBox.add(phoneLabel);
        phoneBox.add(Box.createHorizontalStrut(10));
        phoneBox.add(phoneField);

        //按钮box
        Box btnBox = Box.createHorizontalBox();
        JButton registerBtn= new JButton("注册");
        JButton loginBtn  = new JButton("登录");
        loginBtn.setFont(font);
        registerBtn.setFont(font);
        btnBox.add(registerBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(loginBtn);

        //组装注册相关内容
        registerBox.add(Box.createVerticalStrut(60));
        registerBox.add(userBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(pwBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(rePwBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(phoneBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(btnBox);

        backGroupPanel.add(registerBox);
        jf.add(backGroupPanel);
        jf.setIconImage(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/teaIcon.jpg")));
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        try {
            new RegisterPanel().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
