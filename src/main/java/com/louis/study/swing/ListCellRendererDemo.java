package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class ListCellRendererDemo {

    private JFrame mainWin = new JFrame("好友列表");
    private String[] friends = new String[]{
            "tao",
            "feng",
            "xia",
            "ying",
            "meng"
    };
    //定义一个Jlist对象
    private JList friendsList = new JList(friends);
    public void init(){
        //设置该JList使用ImageCellRenderer作为列表项绘制器
        friendsList.setCellRenderer(new ImageCellRender());
        mainWin.add(new JScrollPane(friendsList));
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public static void main(String[] args) {
        new ListCellRendererDemo().init();
    }
}

class ImageCellRender extends JPanel implements ListCellRenderer{

    private ImageIcon icon;
    private String name;
    //定义绘制单元格时的背景色
    private Color background;
    //定义绘制单元格时的前景色
    private Color foreground;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        icon = new ImageIcon("src/main/resources/images/girl/"+value+".jpg");
        name = value.toString();
        background = isSelected ? list.getSelectionBackground() : list.getBackground();
        foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
        //返回该Jpanel对象作为列表项绘制器
        return this;
    }

    //重写paintComponent()方法，改变JPanel的外观

    @Override
    protected void paintComponent(Graphics g) {
        int imageWidth = icon.getImage().getWidth(null);
        int imageHeight = icon.getImage().getHeight(null);

        g.setColor(background);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(foreground);
        //绘制好友图标
        g.drawImage(icon.getImage().getScaledInstance(getWidth()/2-imageWidth/2,20,Image.SCALE_DEFAULT),getWidth()/2-imageWidth/2,10,null);
        g.setFont(new Font("SanasSerif",Font.BOLD,18));
        //绘制好友用户名
        g.drawString(name,getWidth()/2-name.length()*10,imageHeight+30);
    }

    public Dimension getPreferredSize(){
        return new Dimension(60,80);
    }
}
