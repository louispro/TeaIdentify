package com.louis.study.awt;

import java.awt.*;
import java.awt.event.*;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class MenuDemo{

    Frame frame = new Frame("菜单");

    MenuBar menuBar = new MenuBar();

    Menu file = new Menu("文件");
    Menu edit = new Menu("编辑");

    MenuItem create = new MenuItem("新建");
    MenuItem save = new MenuItem("保存");
    MenuItem open = new MenuItem("打开");
    MenuItem exit = new MenuItem("退出");

    MenuItem nextLine = new MenuItem("自动换行");
    MenuItem copy = new MenuItem("复制");
    MenuItem paste = new MenuItem("粘贴");
    Menu format = new Menu("格式");

    MenuItem comment = new MenuItem("注释",new MenuShortcut(KeyEvent.VK_Q,true));
    MenuItem delComment = new MenuItem("取消注释");

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
