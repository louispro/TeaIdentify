package com.louis.study.swing;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class JColorChooserText {

    JFrame jFrame = new JFrame();

    //��ͼ�����
    private final int CANVAS_WIDTH = 500;
    private final int CANCAS_HEIGHT = 400;

    //�����ϴ�����϶��¼����������
    private int preX = -1;
    private int preY = -1;

    //����һ���Ҽ��˵��������û�����ɫ
    JPopupMenu jPopupMenu = new JPopupMenu();
    JMenuItem chooseColor = new JMenuItem("ѡ����ɫ");
    //����һ��BufferedImage����
    BufferedImage image = new BufferedImage(CANVAS_WIDTH,CANCAS_HEIGHT,BufferedImage.TYPE_INT_RGB);

    public void init(){

    }

    public static void main(String[] args) {
        new JColorChooserText().init();
    }
}
