package com.louis.teaSystemClient.Component;

import javax.swing.*;
import java.awt.*;

/**
 * 赖小燚
 * www.louis.com
 */
public class BackgroundPanel extends JPanel {
    //背景图片
    private Image backIcon;

    public BackgroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
