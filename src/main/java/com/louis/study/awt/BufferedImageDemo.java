package com.louis.study.awt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class BufferedImageDemo {

    //�����߶�
    private final int AREA_WIDTH = 500;
    private final int AREA_HEIGHT = 400;

    //��¼��һ������϶��¼����������
    private int preX = -1;
    private int preY = -1;

    //����һ���Ҽ��˵��������û�����ɫ
    PopupMenu pop = new PopupMenu();
    MenuItem redMenu = new MenuItem("��ɫ");
    MenuItem greenMenu = new MenuItem("��ɫ");
    MenuItem blueMenu = new MenuItem("��ɫ");

    //����һ��BufferedImage����
    BufferedImage image = new BufferedImage(AREA_WIDTH,AREA_HEIGHT,BufferedImage.TYPE_INT_BGR);
    //��ȡimage�����Graphics����
    Graphics graphics = image.getGraphics();

    Frame frame = new Frame("�ֻ����");

    DrawCanvas drawCanvas = new DrawCanvas();

    //���滭����ɫ
    Color foreColor = new Color(255,0,0);


    public void init(){

        //���˵�����ϳ��Ҽ��˵�
        pop.add(redMenu);
        pop.add(greenMenu);
        pop.add(blueMenu);

        //���Ҽ��˵���ӵ�drawCanvas��
        drawCanvas.add(pop);
        graphics.fillRect(0,0,AREA_WIDTH,AREA_HEIGHT);
        drawCanvas.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));

        frame.add(drawCanvas);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        //�Ҽ��˵��¼�
        ActionListener menuListener = e -> {
            if("��ɫ".equals(e.getActionCommand())){
                foreColor = new Color(0,255,0);
            }
            if("��ɫ".equals(e.getActionCommand())){
                foreColor = new Color(255,0,0);
            }
            if("��ɫ".equals(e.getActionCommand())){
                foreColor = new Color(0,0,255);
            }
        };

        //�رմ���
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //��������ƶ�����
        drawCanvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(preX > 0 && preY > 0){
                    graphics.setColor(foreColor);
                    graphics.drawLine(preX,preY,e.getX(), e.getY());
                }
                preX = e.getX();
                preY = e.getY();
                drawCanvas.repaint();
            }
        });

        //�Ҽ��˵�
        drawCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //�����Ҽ��˵�
                if(e.isPopupTrigger()){
                    pop.show(drawCanvas,e.getX(),e.getY());
                }
                preX = -1;
                preY = -1;
            }
        });

        redMenu.addActionListener(menuListener);
        greenMenu.addActionListener(menuListener);
        blueMenu.addActionListener(menuListener);

    }

    public static void main(String[] args) {
        new BufferedImageDemo().init();
    }

    class DrawCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            g.drawImage(image,0,0,null);
        }
    }
}
