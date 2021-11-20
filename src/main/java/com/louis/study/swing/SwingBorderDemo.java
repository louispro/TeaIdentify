package com.louis.study.swing;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class SwingBorderDemo {

    JFrame jFrame = new JFrame("边框");

    public void init(){
        jFrame.setLayout(new GridLayout(2,4));

        //创建BevelBorder
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.RED,Color.GREEN,Color.blue,Color.GRAY);
        jFrame.add(getJPanelWithBorder(bevelBorder,"bevelBorder"));

        Border lineBoder = BorderFactory.createLineBorder(Color.orange,10);
        jFrame.add(getJPanelWithBorder(lineBoder,"lineBorder"));
        Border emptyBorder = BorderFactory.createEmptyBorder(10,5,20,10);
        jFrame.add(getJPanelWithBorder(emptyBorder,"emptyBorder"));
        Border etchedBoder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.RED,Color.GREEN);
        jFrame.add(getJPanelWithBorder(etchedBoder,"etchedBorder"));
        TitledBorder titleBoder = new TitledBorder(new LineBorder(Color.ORANGE,10),"测试标题");
        jFrame.add(getJPanelWithBorder(titleBoder,"titleBorder"));
        CompoundBorder compoundBorder = new CompoundBorder(titleBoder,new LineBorder(Color.RED,10));
        jFrame.add(getJPanelWithBorder(compoundBorder,"compoundBorder"));
        MatteBorder matteBorder = new MatteBorder(10,5,20,10,Color.GREEN);
        jFrame.add(getJPanelWithBorder(matteBorder,"matteBorder"));

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();

    }

    public JPanel getJPanelWithBorder(Border border, String content){
        JPanel jPanel = new JPanel();
        jPanel.setBorder(border);
        jPanel.add(new JLabel(content));
        return jPanel;
    }

    public static void main(String[] args) {
        SwingBorderDemo swingBorderDemo = new SwingBorderDemo();
        swingBorderDemo.init();
    }
}
