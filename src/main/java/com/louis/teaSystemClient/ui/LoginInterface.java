package com.louis.teaSystemClient.ui;


import com.louis.teaSystemClient.Component.BackgroundPanel;
import com.louis.teaSystemClient.util.JsonUtils;
import com.louis.teaSystemClient.util.NetUtil;
import com.louis.teaSystemClient.util.PostUtils;
import com.louis.teaSystemClient.util.ScreenUtils;
import com.louis.teaSystemClient.pojo.ResultInfo;
import com.louis.teaSystemService.pojo.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 赖小燚
 * @www.louis_lai.com
 */
public class LoginInterface {

    JFrame jf = new JFrame("茶叶嫩芽识别系统");
    final int WIDTH = 500;
    final int HEIGHT = 300;
    private boolean hasUsername = false;
    private boolean hasPassword = false;

    //初始化登陆界面
    public void init() throws IOException {
        //设置组件图标
        jf.setIconImage(ImageIO.read(new File("src/main/resources/static/images/teaIdentify/client/tea/tea.png")));
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);

        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File("src/main/resources/static/images/teaIdentify/client/tea/loginBg.png")));

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
        JPasswordField pwField = new JPasswordField(15);
        pwBox.add(pwLabel);
        pwBox.add(Box.createHorizontalStrut(10));
        pwBox.add(pwField);

        //按钮box
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        loginBtn.setEnabled(false); //默认禁用登录按钮，输入之后才能登录
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
                    //请求成功
                    if(info.isFlag()){
                        try{
                            //登陆成功，跳转至系统
                            TeaSystemInterface teaSystemInterface = new TeaSystemInterface();
                            User user = new User();
                            user.setUsername(username);
                            user.setPassword(password);
                            teaSystemInterface.setUser(user);   //初始化主页面的用户信息
                            teaSystemInterface.init();  //初始化主页面
                            jf.dispose();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }else {
                        //登录失败
                        JOptionPane.showMessageDialog(jf,info.getMessage());
                    }
                },/**请求失败**/()-> JOptionPane.showMessageDialog(jf,"网络异常，请稍后重试"));
            }
        });

        //注册事件
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new RegisterInterface().init();
                    jf.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //监听文本框内容改变事件
        Document userDt = userField.getDocument();
        userDt.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasUsername = true;
                if(hasUsername && hasPassword){
                    loginBtn.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                if(d.getLength()==0)    hasUsername = false;    //清空文本框时改变标识
                if(hasUsername && hasPassword){
                    loginBtn.setEnabled(true);
                }else{
                    loginBtn.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        Document passwordDt = pwField.getDocument();
        passwordDt.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasPassword = true;
                if(hasUsername && hasPassword){
                    loginBtn.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                if(d.getLength()==0)    hasPassword = false;    //清空文本框时改变标识
                if(hasUsername && hasPassword){
                    loginBtn.setEnabled(true);
                }else {
                    loginBtn.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void checkWebAndService(){
        if(NetUtil.checkWeb()==false){
            JOptionPane.showMessageDialog(jf,"网络未连接");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(NetUtil.checkService()==false){
            JOptionPane.showMessageDialog(jf,"远程服务器未开启");
        }
    }


    //主窗口
    public static void main(String[] args) {
        try {
            LoginInterface loginInterface = new LoginInterface();
            loginInterface.init();
            loginInterface.checkWebAndService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
