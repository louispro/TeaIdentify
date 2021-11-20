package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JColorChooseDemo {
    JFrame jFrame = new JFrame("颜色选择器");

    JTextArea jTextArea = new JTextArea(6,30);

    JButton jButton = new JButton(new AbstractAction("改变文本框背景颜色") {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = JColorChooser.showDialog(jFrame,"颜色选择器", Color.white);
            jTextArea.setBackground(color);
        }
    });

    public void init(){
        jFrame.add(jTextArea,BorderLayout.NORTH);
        jFrame.add(jButton);

        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JColorChooseDemo().init();
    }
}
