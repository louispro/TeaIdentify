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
 * @��С�D
 * @www.louis_lai.com
 */
public class DropTargetTest {
    final int DESKTOP_WIDTH = 480;
    final int DESKTOP_HEIGHT = 360;
    final int FRAME_DISTANCE = 30;

    JFrame jf = new JFrame("�����Ϸ�Ŀ��--��ͼƬ����ô���");
    //����һ����������
    private JDesktopPane desktopPane = new JDesktopPane();
    //������һ���ڲ����ڵ������
    private int nextFrameX;
    private int nextFrameY;
    //�����ڲ�����Ϊ���������1/2��С
    private int width = DESKTOP_WIDTH/2;
    private int height = DESKTOP_HEIGHT/2;

    public void init(){
        desktopPane.setPreferredSize(new Dimension(DESKTOP_WIDTH,DESKTOP_HEIGHT));
        //����ǰ���ڴ������Ϸ�Ŀ��
        new DropTarget(jf,DnDConstants.ACTION_COPY,new ImageDropTargetListener());
        jf.add(desktopPane);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    class ImageDropTargetListener extends DropTargetAdapter{

        @Override
        public void drop(DropTargetDropEvent event) {
            //���ܸ��Ʋ���
            event.acceptDrop(DnDConstants.ACTION_COPY);
            //��ȡ�Ϸŵ�����
            Transferable transferable = event.getTransferable();
            DataFlavor[] flavors = transferable.getTransferDataFlavors();
            //�����Ϸ���������������ݸ�ʽ
            for(int i = 0;i < flavors.length;i++){
                DataFlavor d = flavors[i];
                try{
                    //ȡ���Ϸ����ݵ����ݸ�ʽ���ļ��б�
                    if(d.equals(DataFlavor.javaFileListFlavor)){
                        //ȡ���Ϸ����ݵ����ݸ�ʽ���ļ��б�
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
                    //ǿ���ϷŲ���������ֹͣ�����Ϸ�Ŀ��
                    event.dropComplete(true);
                    JOptionPane.showInternalMessageDialog(desktopPane,"ϵͳ��֧���������͵��ļ�");
                    return;
                }
                ImageIcon icon = new ImageIcon(image);
                //�����ڲ�������ʾ��ͼƬ
                JInternalFrame iframe = new JInternalFrame(f.getName(),true,true,true,true);
                JLabel imageLabel = new JLabel(icon);
                iframe.add(new JScrollPane(imageLabel));
                desktopPane.add(iframe);
                //�����ڲ����ڵ�ԭʼλ��
                iframe.reshape(nextFrameX,nextFrameY,width,height);
                iframe.show();
                //������һ���ڲ����ڵ�λ��
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
