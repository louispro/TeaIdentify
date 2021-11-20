package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class SToolBarDemo {
    JFrame jFrame = new JFrame("������");
    JToolBar jToolBar = new JToolBar();
    JTextArea jTextArea = new JTextArea(6,35);


    Action pre = new AbstractAction("��һ��",new ImageIcon("src/main/resources/images/component/pre.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            jTextArea.append("��һ��.\n");
        }
    };

    Action pause = new AbstractAction("��ͣ",new ImageIcon("src/main/resources/images/component/pause.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            jTextArea.append("��ͣ����.\n");
        }
    };

    Action next = new AbstractAction("��һ��",new ImageIcon("src/main/resources/images/component/next.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            jTextArea.append("��һ��.\n");
        }
    };

    public void init(){
        jToolBar.add(pre);
        jToolBar.addSeparator();
        jToolBar.add(pause);
        jToolBar.addSeparator();
        jToolBar.add(next);

        jFrame.add(jToolBar,BorderLayout.NORTH);
        jFrame.add(jTextArea);

        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SToolBarDemo sToolBarDemo = new SToolBarDemo();
        sToolBarDemo.init();
    }
}
