package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JProgressBarDemo {
    JFrame jf = new JFrame("测试进度条");
    JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL);
    JCheckBox indeterminate = new JCheckBox("不确定进度");
    JCheckBox noBorder = new JCheckBox("不会只边框");

    public void init(){
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(indeterminate);
        box.add(noBorder);
        jf.setLayout(new FlowLayout());
        jf.add(box);
        //把进度条添加到JFrame窗口中
        jf.add(bar);
        //设置进度条的最大值和最小值
        bar.setMinimum(0);
        bar.setMaximum(100);
        //设置在进度条中绘制完成百分比
        bar.setStringPainted(true);
        //根据该选择框决定是否绘制进度条边框
        noBorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bar.setBorderPainted(!noBorder.isSelected());
            }
        });
        indeterminate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bar.setIndeterminate((indeterminate.isSelected()));
                bar.setStringPainted(!indeterminate.isSelected());
            }
        });
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

        //采用循环方式来不断改变进度条的完成进度
        for(int i = 0;i <= 100;i++){
            bar.setValue(i);
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new JProgressBarDemo().init();
    }
}
