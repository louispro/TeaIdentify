package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class SwingDndSupport {
    JFrame jf = new JFrame("Swing���Ϸ�֧��");
    JTextArea srcTxt = new JTextArea(8,30);
    JTextField jtf = new JTextField(34);
    public void init(){
        srcTxt.append("Swing���Ϸ�֧��.\n");
        srcTxt.append("�����ı��������������������.\n");
        //�����ı���͵����ı�����Ϸ�֧��
        srcTxt.setDragEnabled(true);
        jtf.setDragEnabled(true);
        jf.add(new JScrollPane(srcTxt));
        jf.add(jtf, BorderLayout.SOUTH);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new SwingDndSupport().init();
    }
}
