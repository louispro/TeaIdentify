package com.louis.teaSystemClient.ui;

import com.louis.teaSystemClient.util.ScreenUtils;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaSystemInterface {

    JFrame jFrame = new JFrame("茶叶嫩芽识别系统");

    public void init(){
        //设置菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        JMenu setting = new JMenu("设置");
        JMenu file = new JMenu("文件");
        JMenu menu = new JMenu("菜单");
        JMenu help = new JMenu("帮助");

        JMenuItem changeUser = new JMenuItem("切换账号");
        JMenuItem exit = new JMenuItem("退出");
        setting.add(changeUser);
        setting.add(exit);
        jMenuBar.add(setting);
        jMenuBar.add(file);
        jMenuBar.add(menu);
        jMenuBar.add(help);
        jFrame.setJMenuBar(jMenuBar);

        jFrame.setSize(ScreenUtils.getScreenWidth(),ScreenUtils.getScreenHeight());
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        //菜单栏事件
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface().init();
                    jFrame.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new TeaSystemInterface().init();
    }
}
