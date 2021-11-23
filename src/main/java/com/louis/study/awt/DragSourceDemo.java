package com.louis.study.awt;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class DragSourceDemo {
    JFrame jf = new JFrame("Swing���Ϸ�֧��");
    JLabel srcLabel = new JLabel("Swing���Ϸ�֧��.\n"+"�����ı�����ı�����������������");

    public void init(){
        DragSource dragSource = DragSource.getDefaultDragSource();
        //��SrcLabelת�����Ϸ�Դ
        dragSource.createDefaultDragGestureRecognizer(srcLabel, DnDConstants.ACTION_COPY_OR_MOVE, new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent dge) {
                String txt = srcLabel.getText();
                Transferable transferable = new StringSelection(txt);
                dge.startDrag(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR),transferable);
            }
        });
        jf.add(new JScrollPane(srcLabel));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.pack();
    }

    public static void main(String[] args) {
        new DragSourceDemo().init();
    }
}
