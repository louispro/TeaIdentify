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
 * @��С�D
 * @www.louis_lai.com
 */
public class MainInterface {

    JFrame jf = new JFrame("��Ҷ��ѿʶ��ϵͳ");
    final int WIDTH = 500;
    final int HEIGHT = 300;

    //��װ��ͼ
    public void init() throws IOException {
        //���ô����������
        jf.setIconImage(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/teaIcon.jpg")));
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        //���ô�������
        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/loginBg.png")));

        //font����
        Font font = new Font("����",Font.BOLD,16);

        //��װ�û���
        Box userBox = Box.createHorizontalBox();
        JLabel userLabel = new JLabel("�û�����");
        userLabel.setFont(font);
        JTextField userField = new JTextField(15);
        userBox.add(userLabel);
        userBox.add(Box.createHorizontalStrut(10));
        userBox.add(userField);

        //��װ����
        Box pwBox = Box.createHorizontalBox();
        JLabel pwLabel = new JLabel("��  �룺");
        pwLabel.setFont(font);
        JTextField pwField = new JTextField(15);
        pwBox.add(pwLabel);
        pwBox.add(Box.createHorizontalStrut(10));
        pwBox.add(pwField);

        //��װ��ť
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("��¼");
        JButton registerBtn = new JButton("ע��");
        loginBtn.setFont(font);
        registerBtn.setFont(font);
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(registerBtn);


        //��װ��¼��ص�����
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
                //��ȡ�û���������
                String username = userField.getText();
                String password = pwField.getText();
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                //���ʵ�¼�ӿ�
                PostUtils.postWithParams("http://localhost:8080/user/login",params,result -> {
                    ResultInfo info = JsonUtils.parseResult(result);
                    System.out.println("info:"+info);
                    //�ж�info�е�flag���
                    if(info.isFlag()){
                        //��½�ɹ�����ת����ҳ��
                        try{
                            JOptionPane.showMessageDialog(jf,info.getMessage());
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(jf,info.getMessage());
                    }
                },/**����ʧ��**/()-> JOptionPane.showMessageDialog(jf,"�����쳣�����Ժ�����"));
            }
        });
    }

    //�ͻ��˳�������
    public static void main(String[] args) {
        try {
            new MainInterface().init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
