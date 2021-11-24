package com.louis.TeaIdentify.ui;

import com.louis.TeaIdentify.Component.BackGroupPanel;
import com.louis.TeaIdentify.util.ScreenUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        jf.setBounds((ScreenUtil.getScreenWidth()-WIDTH)/2,(ScreenUtil.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        //���ô�������
        BackGroupPanel bgPanel = new BackGroupPanel(ImageIO.read(new File("src/main/resources/images/teaIdentify/tea/loginBg.png")));

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
