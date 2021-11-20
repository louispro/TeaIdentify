package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class SToolBarDemo {
    JFrame jFrame = new JFrame("工具条");
    JToolBar jToolBar = new JToolBar();
    JTextArea jTextArea = new JTextArea(6,35);


    Action pre = new AbstractAction("上一曲",new ImageIcon("src/main/resources/images/component/pre.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            jTextArea.append("上一曲.\n");
        }
    };

    Action pause = new AbstractAction("暂停",new ImageIcon("src/main/resources/images/component/pause.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            jTextArea.append("暂停播放.\n");
        }
    };

    Action next = new AbstractAction("下一曲",new ImageIcon("src/main/resources/images/component/next.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            jTextArea.append("下一曲.\n");
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
