package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class ListCellRendererDemo {

    private JFrame mainWin = new JFrame("�����б�");
    private String[] friends = new String[]{
            "tao",
            "feng",
            "xia",
            "ying",
            "meng"
    };
    //����һ��Jlist����
    private JList friendsList = new JList(friends);
    public void init(){
        //���ø�JListʹ��ImageCellRenderer��Ϊ�б��������
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
    //������Ƶ�Ԫ��ʱ�ı���ɫ
    private Color background;
    //������Ƶ�Ԫ��ʱ��ǰ��ɫ
    private Color foreground;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        icon = new ImageIcon("src/main/resources/images/girl/"+value+".jpg");
        name = value.toString();
        background = isSelected ? list.getSelectionBackground() : list.getBackground();
        foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
        //���ظ�Jpanel������Ϊ�б��������
        return this;
    }

    //��дpaintComponent()�������ı�JPanel�����

    @Override
    protected void paintComponent(Graphics g) {
        int imageWidth = icon.getImage().getWidth(null);
        int imageHeight = icon.getImage().getHeight(null);

        g.setColor(background);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(foreground);
        //���ƺ���ͼ��
        g.drawImage(icon.getImage().getScaledInstance(getWidth()/2-imageWidth/2,20,Image.SCALE_DEFAULT),getWidth()/2-imageWidth/2,10,null);
        g.setFont(new Font("SanasSerif",Font.BOLD,18));
        //���ƺ����û���
        g.drawString(name,getWidth()/2-name.length()*10,imageHeight+30);
    }

    public Dimension getPreferredSize(){
        return new Dimension(60,80);
    }
}
