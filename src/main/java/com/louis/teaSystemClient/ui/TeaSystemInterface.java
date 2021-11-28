package com.louis.teaSystemClient.ui;

import com.louis.teaSystemClient.Component.SystemTree;
import com.louis.teaSystemClient.util.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaSystemInterface {

    JFrame jFrame = new JFrame("茶叶嫩芽识别系统");

    //图像标注界面
    //图像处理界面
    //模型训练界面
    //嫩芽识别界面


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

        //设置分割面板
        JSplitPane splitPane = new JSplitPane();

        splitPane.setContinuousLayout(true);
        splitPane.setDividerLocation(ScreenUtils.getScreenWidth()/10);
        splitPane.setDividerSize(7);

        //设置左侧内容
        SystemTree systemTree = new SystemTree(SystemTree.root);
        systemTree.init();


        //主界面添加组件
        jFrame.setJMenuBar(jMenuBar);
        jFrame.add(splitPane);
        splitPane.setLeftComponent(systemTree);
        splitPane.setRightComponent(new JLabel("嫩芽识别"));

        try {
            jFrame.setIconImage(ImageIO.read(new File("src/main/resources/static/images/teaIdentify/tea/tea.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jFrame.setSize(ScreenUtils.getScreenWidth(),ScreenUtils.getScreenHeight());
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        //菜单栏事件
        changeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LoginInterface().init();
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

        //节点选中事件
        systemTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object leaf = e.getNewLeadSelectionPath().getLastPathComponent();
                if(systemTree.getLabelImage().equals(leaf)){
                    splitPane.setRightComponent(new JLabel("图像标注"));
                    splitPane.setDividerLocation(splitPane.getDividerLocation());
                }
                if(systemTree.getProcessImage().equals(leaf)){
                    splitPane.setRightComponent(new JLabel("图像处理"));
                    splitPane.setDividerLocation(splitPane.getDividerLocation());
                }
                if(systemTree.getTrainModel().equals(leaf)){
                    splitPane.setRightComponent(new JLabel("模型训练"));
                    splitPane.setDividerLocation(splitPane.getDividerLocation());
                }
                if(systemTree.getTeaBudIdentify().equals(leaf)){
                    splitPane.setRightComponent(new JLabel("嫩芽识别"));
                    splitPane.setDividerLocation(splitPane.getDividerLocation());
                }
            }
        });
    }

    //加载相应界面
    public JPanel loadPanel(){
        return null;
    }

    public static void main(String[] args) {
        new TeaSystemInterface().init();
    }
}
