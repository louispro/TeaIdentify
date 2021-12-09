package com.louis.teaSystemClient.ui;

import com.louis.teaSystemClient.Component.BackgroundPanel;
import com.louis.teaSystemClient.pojo.ResultInfo;
import com.louis.teaSystemClient.util.JsonUtils;
import com.louis.teaSystemClient.util.PostUtils;
import com.louis.teaSystemClient.util.ScreenUtils;

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
 * @赖小燚
 * @www.louis_lai.com
 */
public class RegisterInterface {

    JFrame jf = new JFrame("注册");

    final int WIDTH = 500;
    final int HEIGHT = 500;

    private boolean hasUsername = false;
    private boolean hasPassword = false;
    private boolean hasRePassword = false;
    private boolean hasPhone = false;
    private boolean hasSex = false;

    public void init() throws IOException {
        BackgroundPanel backGroupPanel = new BackgroundPanel(ImageIO.read(new File("src/main/resources/static/images/teaIdentify/client/tea/loginBg.png")));

        //font字体
        Font font = new Font("楷体",Font.BOLD,16);

        //注册box
        Box registerBox = Box.createVerticalBox();

        //用户box
        Box userBox = Box.createHorizontalBox();
        JLabel userLabel = new JLabel("用 户 名");
        userLabel.setFont(font);
        JTextField userField = new JTextField(15);
        userBox.add(userLabel);
        userBox.add(Box.createHorizontalStrut(10));
        userBox.add(userField);

        //密码box
        Box pwBox = Box.createHorizontalBox();
        JLabel pwLabel = new JLabel("密    码");
        pwLabel.setFont(font);
        JPasswordField pwField = new JPasswordField(15);
        pwBox.add(pwLabel);
        pwBox.add(Box.createHorizontalStrut(10));
        pwBox.add(pwField);

        //确认密码box
        Box rePwBox = Box.createHorizontalBox();
        JLabel rePwLabel = new JLabel("确认密码");
        rePwLabel.setFont(font);
        JPasswordField rePwField = new JPasswordField(15);
        rePwBox.add(rePwLabel);
        rePwBox.add(Box.createHorizontalStrut(10));
        rePwBox.add(rePwField);

        //手机号box
        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLabel = new JLabel("手 机 号");
        phoneLabel.setFont(font);
        JTextField phoneField = new JTextField(15);
        phoneBox.add(phoneLabel);
        phoneBox.add(Box.createHorizontalStrut(10));
        phoneBox.add(phoneField);

        //性别box
        Box sexBox = Box.createHorizontalBox();
        JLabel sexLabel = new JLabel("性    别");
        sexLabel.setFont(font);
        JRadioButton boyBtn = new JRadioButton("男");
        JRadioButton girlBtn = new JRadioButton("女");
        boyBtn.setFont(font);
        girlBtn.setFont(font);
        ButtonGroup sexBtnGroup = new ButtonGroup();
        sexBtnGroup.add(boyBtn);
        sexBtnGroup.add(girlBtn);
        sexBox.add(sexLabel);
        sexBox.add(Box.createHorizontalStrut(10));
        sexBox.add(boyBtn);
        sexBox.add(Box.createHorizontalStrut(60));
        sexBox.add(girlBtn);
        sexBox.add(Box.createHorizontalStrut(120));


        //登录box
        Box btnBox = Box.createHorizontalBox();
        JButton registerBtn= new JButton("注册");
        JButton loginBtn  = new JButton("登录");
        registerBtn.setEnabled(false);
        loginBtn.setFont(font);
        registerBtn.setFont(font);
        btnBox.add(registerBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(loginBtn);

        //组装注册界面
        registerBox.add(Box.createVerticalStrut(60));
        registerBox.add(userBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(pwBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(rePwBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(phoneBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(sexBox);
        registerBox.add(Box.createVerticalStrut(30));
        registerBox.add(btnBox);

        backGroupPanel.add(registerBox);
        jf.add(backGroupPanel);
        jf.setIconImage(ImageIO.read(new File("src/main/resources/static/images/teaIdentify/client/tea/tea.png")));
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //注册
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户信息
                String username = userField.getText();
                String password = pwField.getText();
                String phone = phoneField.getText();
                String sex = boyBtn.isSelected()?boyBtn.getText():girlBtn.getText();
                //参数
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                params.put("phone",phone);
                params.put("sex",sex);
                //发送post请求
                PostUtils.postWithParams("http://localhost:8080/user/register",params,(result)->{
                    ResultInfo info = JsonUtils.parseResult(result);
                    if(info.isFlag()){
                        //请求成功
                        JOptionPane.showMessageDialog(jf,info.getMessage());
                        try {
                            new LoginInterface().init();
                            jf.dispose();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }else{
                        //请求失败
                        JOptionPane.showMessageDialog(jf,info.getMessage());
                    }
                },()->{
                    JOptionPane.showMessageDialog(jf,"网络异常，请稍后重试");
                });
            }
        });

        //跳转到登录界面
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoginInterface().init();
                    jf.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //监听文本框改变事件
        Document userDoc = userField.getDocument();
        Document passwordDoc = pwField.getDocument();
        Document rePasswordDoc = rePwField.getDocument();
        Document phoneDoc = phoneField.getDocument();
        userDoc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasUsername = true;
                if(hasUsername && hasPassword && hasRePassword && hasPhone  ){
                    registerBtn.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                if(d.getLength()==0) hasUsername = false;
                if(hasUsername && hasPassword && hasRePassword && hasPhone   ){
                    registerBtn.setEnabled(true);
                }else{
                    registerBtn.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        passwordDoc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasPassword = true;
                if(hasUsername && hasPassword && hasRePassword && hasPhone  ){
                    registerBtn.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                if(d.getLength()==0) hasPassword = false;
                if(hasUsername && hasPassword && hasRePassword && hasPhone  ){
                    registerBtn.setEnabled(true);
                }else{
                    registerBtn.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        rePasswordDoc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasRePassword = true;
                if(hasUsername && hasPassword && hasRePassword && hasPhone  ){
                    registerBtn.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                if(d.getLength()==0) hasRePassword = false;
                if(hasUsername && hasPassword && hasRePassword && hasPhone  ){
                    registerBtn.setEnabled(true);
                }else{
                    registerBtn.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        phoneDoc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasPhone = true;
                if(hasUsername && hasPassword && hasRePassword && hasPhone){
                    registerBtn.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Document d = e.getDocument();
                if(d.getLength()==0) hasPhone = false;
                if(hasUsername && hasPassword && hasRePassword && hasPhone){
                    registerBtn.setEnabled(true);
                }else{
                    registerBtn.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        try {
            new RegisterInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
