package com.louis.teaSystemClient.Component;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaBudModelPanel extends JPanel{
    //选择标签文件按钮
    JButton chooseAnnotationBtn = new JButton("选择标签集");
    //选择图片文件按钮
    JButton chooseImageBtn = new JButton("选择图片集");
    //选择图片文件按钮
    JButton uploadBtn = new JButton("上            传");
    //训练按钮
    JButton trainBtn = new JButton(" 开 始 训 练");

    //font字体
    Font font = new Font("楷体",Font.BOLD,16);

    //标签文件
    File[] annotationFiles = null;
    //图片文件
    File[] imageFiles = null;

    public TeaBudModelPanel(){
        Box btnBox = Box.createVerticalBox();
        btnBox.add(Box.createVerticalStrut(200));
        btnBox.add(chooseAnnotationBtn);
        btnBox.add(Box.createVerticalStrut(30));
        btnBox.add(chooseImageBtn);
        btnBox.add(Box.createVerticalStrut(30));
        btnBox.add(uploadBtn);
        btnBox.add(Box.createVerticalStrut(30));
        btnBox.add(trainBtn);
        this.add(btnBox);
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("Model");
        jf.setSize(600,600);
        TeaBudModelPanel model = new TeaBudModelPanel();
        jf.add(model);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
