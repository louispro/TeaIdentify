package com.louis.study.awt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class BufferedImageDemo {

    //画区高度
    private final int AREA_WIDTH = 500;
    private final int AREA_HEIGHT = 400;

    //记录上一次鼠标拖动事件的鼠标坐标
    private int preX = -1;
    private int preY = -1;

    //定义一个右键菜单用于设置画笔颜色
    PopupMenu pop = new PopupMenu();
    MenuItem redMenu = new MenuItem("红色");
    MenuItem greenMenu = new MenuItem("绿色");
    MenuItem blueMenu = new MenuItem("蓝色");

    //定义一个BufferedImage对象
    BufferedImage image = new BufferedImage(AREA_WIDTH,AREA_HEIGHT,BufferedImage.TYPE_INT_BGR);
    //获取image对象的Graphics对象
    Graphics graphics = image.getGraphics();

    Frame frame = new Frame("手绘程序");

    DrawCanvas drawCanvas = new DrawCanvas();

    //保存画笔颜色
    Color foreColor = new Color(255,0,0);


    public void init(){

        //将菜单项组合成右键菜单
        pop.add(redMenu);
        pop.add(greenMenu);
        pop.add(blueMenu);

        //将右键菜单添加到drawCanvas中
        drawCanvas.add(pop);
        graphics.fillRect(0,0,AREA_WIDTH,AREA_HEIGHT);
        drawCanvas.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));

        frame.add(drawCanvas);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        //右键菜单事件
        ActionListener menuListener = e -> {
            if("绿色".equals(e.getActionCommand())){
                foreColor = new Color(0,255,0);
            }
            if("红色".equals(e.getActionCommand())){
                foreColor = new Color(255,0,0);
            }
            if("蓝色".equals(e.getActionCommand())){
                foreColor = new Color(0,0,255);
            }
        };

        //关闭窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //监听鼠标移动动作
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

        //右键菜单
        drawCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //弹出右键菜单
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
