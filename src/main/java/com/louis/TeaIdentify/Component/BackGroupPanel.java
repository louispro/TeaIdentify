package com.louis.TeaIdentify.Component;

import javax.swing.*;
import java.awt.*;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class BackGroupPanel extends JPanel {

    //����ͼƬ
    private Image backIcon;

    public BackGroupPanel(Image backIcon){
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
