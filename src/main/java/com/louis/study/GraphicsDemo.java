package com.louis.study;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class GraphicsDemo {

    private final String RECT_SHAPE = "rect";
    private final String OVAL_SHAPE = "oval";

    Frame frame = new Frame("��ͼ");

    Button buttonRect = new Button("���ƾ���");
    Button buttonOval = new Button("������Բ");

    Panel panel = new Panel();

    String shape = "";

    private class MyCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            if(RECT_SHAPE.equals(shape)){
                //���ƾ���
                g.setColor(Color.GREEN);
                g.drawRect(100,100,150,100);
            }
            if(OVAL_SHAPE.equals(shape)){
                //������Բ
                g.setColor(Color.RED);
                g.drawOval(100 ,100,150,100);
            }
        }

    }

    //�Զ��廭��
    MyCanvas canvas = new MyCanvas();

    public void init(){
        buttonRect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = RECT_SHAPE;
                canvas.repaint();
            }
        });

        buttonOval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = OVAL_SHAPE;
                canvas.repaint();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        panel.add(buttonRect);
        panel.add(buttonOval);
        frame.add(panel,BorderLayout.SOUTH);

        canvas.setSize(300,300);
        frame.add(canvas);
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        GraphicsDemo graphicsDemo = new GraphicsDemo();
        graphicsDemo.init();
    }
}
