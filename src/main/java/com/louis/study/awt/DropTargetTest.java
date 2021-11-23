package com.louis.study.awt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class DropTargetTest {
    final int DESKTOP_WIDTH = 480;
    final int DESKTOP_HEIGHT = 360;
    final int FRAME_DISTANCE = 30;

    JFrame jf = new JFrame("测试拖放目标--把图片拖入该窗口");
    //定义一个虚拟桌面
    private JDesktopPane desktopPane = new JDesktopPane();
    //保存下一个内部窗口的坐标点
    private int nextFrameX;
    private int nextFrameY;
    //定义内部窗口为虚拟桌面的1/2大小
    private int width = DESKTOP_WIDTH/2;
    private int height = DESKTOP_HEIGHT/2;

    public void init(){
        desktopPane.setPreferredSize(new Dimension(DESKTOP_WIDTH,DESKTOP_HEIGHT));
        //将当前窗口创建成拖放目标
        new DropTarget(jf,DnDConstants.ACTION_COPY,new ImageDropTargetListener());
        jf.add(desktopPane);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    class ImageDropTargetListener extends DropTargetAdapter{

        @Override
        public void drop(DropTargetDropEvent event) {
            //接受复制操作
            event.acceptDrop(DnDConstants.ACTION_COPY);
            //获取拖放的内容
            Transferable transferable = event.getTransferable();
            DataFlavor[] flavors = transferable.getTransferDataFlavors();
            //遍历拖放内容里的所有数据格式
            for(int i = 0;i < flavors.length;i++){
                DataFlavor d = flavors[i];
                try{
                    //取出拖放内容的数据格式是文件列表
                    if(d.equals(DataFlavor.javaFileListFlavor)){
                        //取出拖放内容的数据格式是文件列表
                        List fileList = (List)transferable.getTransferData(d);
                        for(Object f : fileList){
                            showImage((File)f,event);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedFlavorException e) {
                    e.printStackTrace();
                }
            }
        }

        private void showImage(File f, DropTargetDropEvent event){
            try {
                Image image = ImageIO.read(f);
                if(image == null){
                    //强制拖放操作结束，停止阻塞拖放目标
                    event.dropComplete(true);
                    JOptionPane.showInternalMessageDialog(desktopPane,"系统不支持这种类型的文件");
                    return;
                }
                ImageIcon icon = new ImageIcon(image);
                //创建内部创库显示该图片
                JInternalFrame iframe = new JInternalFrame(f.getName(),true,true,true,true);
                JLabel imageLabel = new JLabel(icon);
                iframe.add(new JScrollPane(imageLabel));
                desktopPane.add(iframe);
                //设置内部窗口的原始位置
                iframe.reshape(nextFrameX,nextFrameY,width,height);
                iframe.show();
                //计算下一个内部窗口的位置
                nextFrameX += FRAME_DISTANCE;
                nextFrameY += FRAME_DISTANCE;
                if(nextFrameX+width>desktopPane.getWidth()) nextFrameX = 0;
                if(nextFrameY+width>desktopPane.getHeight()) nextFrameY = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DropTargetTest().init();
    }
}
