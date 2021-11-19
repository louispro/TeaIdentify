package com.louis.study;

import java.awt.*;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class PanelDemo {

    public static void main(String[] args) {
        Frame f = new Frame("演示Panel");
        Panel p = new Panel();
        p.add(new TextField("测试文本"));
        p.add(new Button("测试按钮"));
        f.add(p);
        f.setBounds(100,100,500,300);
        f.setVisible(true);
    }

}
