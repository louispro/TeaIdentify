package com.louis.teaSystemClient.render;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 赖小燚
 * www.louis.com
 */
public class SystemTreeRender extends DefaultTreeCellRenderer {
    //初始化五个图标
    private ImageIcon rootIcon;
    private ImageIcon labelIcon;
    private ImageIcon processIcon;
    private ImageIcon trainIcon;
    private ImageIcon identifyIcon;

    public SystemTreeRender(){
            rootIcon  = new ImageIcon("src/main/resources/static/images/teaIdentify/tea/system.png");
            labelIcon = new ImageIcon("src/main/resources/static/images/teaIdentify/tea/label.png");
            processIcon  = new ImageIcon("src/main/resources/static/images/teaIdentify/tea/process.png");
            trainIcon  = new ImageIcon("src/main/resources/static/images/teaIdentify/tea/train.png");
            identifyIcon  = new ImageIcon("src/main/resources/static/images/teaIdentify/tea/identify.png");
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        //使用默认绘制
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        ImageIcon image = null;
        switch (row){
            case 0:
                image = rootIcon;
                break;
            case 1:
                image = labelIcon;
                break;
            case 2:
                image = processIcon;
                break;
            case 3:
                image = trainIcon;
                break;
            case 4:
                image = identifyIcon;
                break;
        }
        this.setIcon(image);
        return this;
    }
}
