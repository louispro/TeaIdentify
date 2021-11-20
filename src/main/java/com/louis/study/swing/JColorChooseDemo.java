package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class JColorChooseDemo {
    JFrame jFrame = new JFrame("��ɫѡ����");

    JTextArea jTextArea = new JTextArea(6,30);

    JButton jButton = new JButton(new AbstractAction("�ı��ı��򱳾���ɫ") {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = JColorChooser.showDialog(jFrame,"��ɫѡ����", Color.white);
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
