package com.louis.study.swing;

import javax.swing.*;
import javax.swing.plaf.metal.MetalDesktopIconUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class JInternalFrameDemo {

    final int DESKTOP_WIDTH = 480;
    final int DESKTOP_HEIGHT = 360;
    final int FRAME_DISTANCE = 30;
    JFrame jFrame = new JFrame("MDI����");
    //����һ����������
    private MyJDesktopPane desktopPane = new MyJDesktopPane();
    //������һ���ڲ����������
    private int nextFrameX;
    private int nextFrameY;
    //�����ڲ�����Ϊ���������1/2��С
    private int width = DESKTOP_WIDTH/2;
    private int height = DESKTOP_HEIGHT/2;
    //Ϊ�����ڶ��������˵�
    JMenu fileMenu = new JMenu("�ļ�");
    JMenu windowMenu = new JMenu("����");
    //����newAction���ڴ����˵��͹��߰�ť
    Action newAction = new AbstractAction("�½�",new ImageIcon("src/main/resources/images/component/new.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            //�����ڲ�����
            final JInternalFrame iframe = new JInternalFrame("���ĵ�",true,true,true,true);
            iframe.add(new JScrollPane(new JTextArea(8,40)));
            //���ڲ�������ӵ�����������
            desktopPane.add(iframe);
            //�����ڲ����ڵ�ԭʼλ��
            iframe.reshape(nextFrameX,nextFrameY,width,height);
            //ʹ�ô��ڿɼ���������ѡ����
            iframe.show();
            nextFrameX += FRAME_DISTANCE;
            nextFrameY += FRAME_DISTANCE;
            if(nextFrameX+width>desktopPane.getWidth()) nextFrameX = 0;
            if(nextFrameY+height>desktopPane.getHeight()) nextFrameY = 0;
        }
    };

    //����exitAction���ڴ����˵��͹��߰�ť
    Action exitAction = new AbstractAction("�˳�",new ImageIcon("src/main/resources/images/component/exit.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    //����һ����������
    public void init(){
        //Ϊ���ڰ�װ�˵����͹�����
        JMenuBar menuBar = new JMenuBar();
        JToolBar toolBar = new JToolBar();
        jFrame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(newAction);
        fileMenu.add(exitAction);
        toolBar.add(newAction);
        toolBar.add(exitAction);
        menuBar.add(windowMenu);
        JMenuItem nextItem = new JMenuItem("��һ��");
        nextItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.selectNextWindows();
            }
        });
        JMenuItem cascadeItem = new JMenuItem("����");
        cascadeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.cascadeWindows(FRAME_DISTANCE,0.75);
            }
        });
        JMenuItem tileItem = new JMenuItem("ƽ��");
        tileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.tileWindows();
            }
        });

        final JCheckBoxMenuItem dragOutlineItem = new JCheckBoxMenuItem("����ʾ�϶����ڵ�����");
        dragOutlineItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.setDragMode((dragOutlineItem.isSelected()?JDesktopPane.OUTLINE_DRAG_MODE:JDesktopPane.LIVE_DRAG_MODE));
            }
        });

        windowMenu.add(nextItem);
        windowMenu.add(cascadeItem);
        windowMenu.add(tileItem);
        windowMenu.add(dragOutlineItem);

        desktopPane.setPreferredSize(new Dimension(480,360));
        jFrame.add(desktopPane);
        jFrame.add(toolBar,BorderLayout.NORTH);
        jFrame.setJMenuBar(menuBar);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }

    public static void main(String[] args) {
        new JInternalFrameDemo().init();
    }
}

class MyJDesktopPane extends JDesktopPane{

    //�����д����Լ�����ʽ��ʾ������offset���������ڵ�λ�ƾ��룬scale���ڲ�������JDesktopPane�Ĵ�С����
    public void cascadeWindows(int offset,double scale){
        //���弶����ʾ����ʱÿ�����ڵ�λ��
        int width = (int)(getWidth()*scale);
        int height = (int)(getWidth()*scale);
        //���ڱ��漶������ʱÿ�����ڵ�λ��
        int x = 0;
        int y = 0;
        for(JInternalFrame frame : getAllFrames()){
            try{
                //ȡ���ڲ����ڵ���󻯣���С��
                frame.setMaximum(false);
                frame.setIcon(false);
                //�Ѵ������·���ָ��λ��
                frame.reshape(x,y,width,height);
                x += offset;
                y += offset;
                //���������������ı߽�
                if(x + width > getWidth()) x = 0;
                if(y + height > getHeight()) y = 0;
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }

    //�����д�����ƽ�̷�ʽ��ʾ
    public void tileWindows(){
        //ͳ�����д���
        int frameCount = 0;
        for(JInternalFrame frame : getAllFrames()){
            frameCount++;
        }
        //������Ҫ�����У������вſ���ƽ�����д���
        int rows = (int)Math.sqrt(frameCount);
        int cols = frameCount/rows;
        //��Ҫ�������ӵ��������еĴ���
        int extra = frameCount%rows;
        //����ƽ��ʱ�ڲ����ڵĴ�С
        int width = getWidth()/cols;
        int height = getHeight()/rows;
        //���ڱ���ƽ�̴���ʱÿ�������ٺ��������ϵ�����
        int x = 0;
        int y = 0;
        for(JInternalFrame frame : getAllFrames()){
            try{
                //ȡ���ڲ����ڵ������С��
                frame.setMaximum(false);
                frame.setIcon(false);
                frame.reshape(x*width,y*height,width,height);
                y++;
                //ÿ����һ�д���
                if(y == rows){
                    //��ʼ�ŷ���һ�д���
                    y = 0;
                    x++;
                    //����������Ĵ�����ʣ�µ��������
                    //����������ж���Ҫ������һ������
                    if(extra == cols-x){
                        rows ++;
                        height = getHeight()/rows;
                    }
                }
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }

    //ѡ����һ����ͼ�괰��
    public void selectNextWindows(){
        JInternalFrame[] frames = getAllFrames();
        for(int i = 0;i < frames.length;i++){
            if(frames[i].isSelected()){
                //�ҳ���һ������С�����ڣ�����ѡ���������ѡ��ʧ�ܣ����������ѡ����һ������
                int next = (i+1)%frames.length;
                while ((next != 1)){
                    //����ô��ڲ��Ǵ�����С��״̬
                    if(!frames[next].isIcon()){
                        try{
                            frames[next].setSelected(true);
                            frames[next].toFront();
                            frames[i].toBack();
                            return;
                        } catch (PropertyVetoException e) {
                            e.printStackTrace();
                        }
                        next = (next + 1)%frames.length;
                    }
                }
            }
        }
    }
}
