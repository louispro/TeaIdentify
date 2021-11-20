package com.louis.study.awt;

import java.awt.*;
import java.awt.event.*;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class MenuDemo{

    Frame frame = new Frame("�˵�");

    MenuBar menuBar = new MenuBar();

    Menu file = new Menu("�ļ�");
    Menu edit = new Menu("�༭");

    MenuItem create = new MenuItem("�½�");
    MenuItem save = new MenuItem("����");
    MenuItem open = new MenuItem("��");
    MenuItem exit = new MenuItem("�˳�");

    MenuItem nextLine = new MenuItem("�Զ�����");
    MenuItem copy = new MenuItem("����");
    MenuItem paste = new MenuItem("ճ��");
    Menu format = new Menu("��ʽ");

    MenuItem comment = new MenuItem("ע��",new MenuShortcut(KeyEvent.VK_Q,true));
    MenuItem delComment = new MenuItem("ȡ��ע��");

    PopupMenu popupMenu = new PopupMenu();

    public void init(){
        frame.setMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);

        file.add(create);
        file.add(save);
        file.add(open);
        file.add(exit);

        edit.add(nextLine);
        edit.add(copy);
        edit.add(paste);
        edit.addSeparator();
        edit.add(format);

        format.add(comment);
        format.add(delComment);

        popupMenu.add(comment);
        popupMenu.add(delComment);
        popupMenu.add(copy);
        popupMenu.add(paste);

        frame.add(popupMenu);
        frame.add(new TextArea(),BorderLayout.SOUTH);
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                boolean flag = e.isPopupTrigger();
                if(flag){
                    popupMenu.show(frame,e.getX(),e.getY());
                }
            }
        });
    }

    public static void main(String[] args) {
        MenuDemo menuDemo = new MenuDemo();
        menuDemo.init();
    }
}
