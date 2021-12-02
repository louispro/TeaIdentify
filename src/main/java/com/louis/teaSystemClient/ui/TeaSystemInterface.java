package com.louis.teaSystemClient.ui;

import com.louis.teaSystemClient.Component.SystemTree;
import com.louis.teaSystemClient.Component.TeaBudIdentifyPanel;
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

    //窗口宽高
    Rectangle rectangle;

    JFrame jFrame = new JFrame("茶叶嫩芽识别系统",GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());

    //图像标注界面
    JPanel labelImage = new JPanel();
    //图像处理界面
    JPanel processImage = new JPanel();
    //模型训练界面
    JPanel trainModel = new JPanel();
    //嫩芽识别界面
    TeaBudIdentifyPanel teaBudIdentifyPanel = new TeaBudIdentifyPanel();


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
        splitPane.setRightComponent(teaBudIdentifyPanel);

        try {
            jFrame.setIconImage(ImageIO.read(new File("src/main/resources/static/images/teaIdentify/tea/tea.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jFrame.setBounds(new Rectangle(0,0,ScreenUtils.getScreenWidth(),ScreenUtils.getScreenHeight()-ScreenUtils.getInset()));
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        jFrame.setM
        rectangle = jFrame.getBounds();
        System.out.println(jFrame.getSize());
        System.out.println(rectangle+"   8");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                    splitPane.setRightComponent(teaBudIdentifyPanel);
                    splitPane.setDividerLocation(splitPane.getDividerLocation());
                }
            }
        });

        //点击任务栏事件
//        jFrame.addWindowStateListener(new WindowStateListener() {
//            @Override
//            public void windowStateChanged(WindowEvent e) {
//                if(e.getNewState() == 1 || e.getNewState() == 7) {  //窗口最小化
//                    System.out.println("窗口最小化");
//                }else if(e.getNewState() == 0) {    //窗口恢复到初始状态
//                    System.out.println("窗口恢复到初始状态");
//                }else if(e.getNewState() == 6) {
//                    System.out.println("窗口最大化");
//                }
//            }
//        });
    }

    //加载嫩芽识别界面
    public JPanel loadTeaBudIdentifyPanel(){
        return null;
    }

    public static void main(String[] args) {
        new TeaSystemInterface().init();
    }
}
