package com.louis.study.swing;

import javax.swing.*;
import javax.swing.plaf.metal.MetalDesktopIconUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JInternalFrameDemo {

    final int DESKTOP_WIDTH = 480;
    final int DESKTOP_HEIGHT = 360;
    final int FRAME_DISTANCE = 30;
    JFrame jFrame = new JFrame("MDI界面");
    //定义一个虚拟桌面
    private MyJDesktopPane desktopPane = new MyJDesktopPane();
    //保存下一个内部窗口坐标点
    private int nextFrameX;
    private int nextFrameY;
    //定义内部窗口为虚拟桌面的1/2大小
    private int width = DESKTOP_WIDTH/2;
    private int height = DESKTOP_HEIGHT/2;
    //为主窗口定义两个菜单
    JMenu fileMenu = new JMenu("文件");
    JMenu windowMenu = new JMenu("窗口");
    //定义newAction用于创建菜单和工具按钮
    Action newAction = new AbstractAction("新建",new ImageIcon("src/main/resources/images/component/new.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            //创建内部窗口
            final JInternalFrame iframe = new JInternalFrame("新文档",true,true,true,true);
            iframe.add(new JScrollPane(new JTextArea(8,40)));
            //将内部窗口添加到虚拟桌面中
            desktopPane.add(iframe);
            //设置内部窗口的原始位置
            iframe.reshape(nextFrameX,nextFrameY,width,height);
            //使该窗口可见，并尝试选中它
            iframe.show();
            nextFrameX += FRAME_DISTANCE;
            nextFrameY += FRAME_DISTANCE;
            if(nextFrameX+width>desktopPane.getWidth()) nextFrameX = 0;
            if(nextFrameY+height>desktopPane.getHeight()) nextFrameY = 0;
        }
    };

    //定义exitAction用于创建菜单和工具按钮
    Action exitAction = new AbstractAction("退出",new ImageIcon("src/main/resources/images/component/exit.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    //定义一个虚拟桌面
    public void init(){
        //为窗口安装菜单条和工具条
        JMenuBar menuBar = new JMenuBar();
        JToolBar toolBar = new JToolBar();
        jFrame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(newAction);
        fileMenu.add(exitAction);
        toolBar.add(newAction);
        toolBar.add(exitAction);
        menuBar.add(windowMenu);
        JMenuItem nextItem = new JMenuItem("下一个");
        nextItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.selectNextWindows();
            }
        });
        JMenuItem cascadeItem = new JMenuItem("级联");
        cascadeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.cascadeWindows(FRAME_DISTANCE,0.75);
            }
        });
        JMenuItem tileItem = new JMenuItem("平铺");
        tileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktopPane.tileWindows();
            }
        });

        final JCheckBoxMenuItem dragOutlineItem = new JCheckBoxMenuItem("仅显示拖动窗口的轮廓");
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

    //将所有窗口以级联方式显示，其中offset是两个窗口的位移距离，scale是内部窗口与JDesktopPane的大小比例
    public void cascadeWindows(int offset,double scale){
        //定义级联显示窗口时每个窗口的位置
        int width = (int)(getWidth()*scale);
        int height = (int)(getWidth()*scale);
        //用于保存级联窗口时每个窗口的位置
        int x = 0;
        int y = 0;
        for(JInternalFrame frame : getAllFrames()){
            try{
                //取消内部窗口的最大化，最小化
                frame.setMaximum(false);
                frame.setIcon(false);
                //把窗口重新放在指定位置
                frame.reshape(x,y,width,height);
                x += offset;
                y += offset;
                //如果到了虚拟桌面的边界
                if(x + width > getWidth()) x = 0;
                if(y + height > getHeight()) y = 0;
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }

    //将所有窗口以平铺方式显示
    public void tileWindows(){
        //统计所有窗口
        int frameCount = 0;
        for(JInternalFrame frame : getAllFrames()){
            frameCount++;
        }
        //计算需要多少行，多少列才可以平铺所有窗口
        int rows = (int)Math.sqrt(frameCount);
        int cols = frameCount/rows;
        //需要额外增加到其他列中的窗口
        int extra = frameCount%rows;
        //计算平铺时内部窗口的大小
        int width = getWidth()/cols;
        int height = getHeight()/rows;
        //用于保存平铺窗口时每个窗口再横向，纵向上的索引
        int x = 0;
        int y = 0;
        for(JInternalFrame frame : getAllFrames()){
            try{
                //取消内部窗口的最大化最小化
                frame.setMaximum(false);
                frame.setIcon(false);
                frame.reshape(x*width,y*height,width,height);
                y++;
                //每排完一列窗口
                if(y == rows){
                    //开始排放下一列窗口
                    y = 0;
                    x++;
                    //如果额外多出的窗口与剩下的列数相等
                    //则后面所有列都需要多排列一个窗口
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

    //选中下一个非图标窗口
    public void selectNextWindows(){
        JInternalFrame[] frames = getAllFrames();
        for(int i = 0;i < frames.length;i++){
            if(frames[i].isSelected()){
                //找出下一个非最小化窗口，尝试选中它，如果选中失败，则继续尝试选中下一个窗口
                int next = (i+1)%frames.length;
                while ((next != 1)){
                    //如果该窗口不是处于最小化状态
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
