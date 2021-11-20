package com.louis.study.swing;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JColorChooserText {

    JFrame jFrame = new JFrame();

    //画图区宽高
    private final int CANVAS_WIDTH = 500;
    private final int CANCAS_HEIGHT = 400;

    //保存上次鼠标拖动事件的鼠标坐标
    private int preX = -1;
    private int preY = -1;

    //定义一个右键菜单用于设置画笔颜色
    JPopupMenu jPopupMenu = new JPopupMenu();
    JMenuItem chooseColor = new JMenuItem("选择颜色");
    //定义一个BufferedImage对象
    BufferedImage image = new BufferedImage(CANVAS_WIDTH,CANCAS_HEIGHT,BufferedImage.TYPE_INT_RGB);

    public void init(){

    }

    public static void main(String[] args) {
        new JColorChooserText().init();
    }
}
