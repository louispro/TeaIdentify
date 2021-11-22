package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @ÀµĞ¡ D
 * @www.louis_lai.com
 */
public class JLayeredPaneTest {
    JFrame jf = new JFrame("²âÊÔJLayeredPane");
    JLayeredPane jLayeredPane = new JLayeredPane();


    public void init(){
        //ÏòlayeredPaneÌí¼Ó3¸ö×é¼ş
        jLayeredPane.add(new ContentPanel(10,20,"ÓîÖÇ²¨×ôÖú","src/main/resources/images/book/sasiki.png"),JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.add(new ContentPanel(100,60,"ÓîÖÇ²¨×ôÖú","src/main/resources/images/book/sasiki.png"),JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.add(new ContentPanel(190,100,"ÓîÖÇ²¨×ôÖú","src/main/resources/images/book/sasiki.png"),JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.setPreferredSize(new Dimension(400,300));
        jLayeredPane.setVisible(true);

        jLayeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentPanel current = (ContentPanel) jLayeredPane.getComponentAt(e.getX(),e.getY());
                jLayeredPane.moveToFront(current);
            }
        });

        jf.add(jLayeredPane);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new JLayeredPaneTest().init();
    }


}

class ContentPanel extends  JPanel{
    public ContentPanel(int xPos,int yPos,String title,String ico){
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title));
        JLabel label = new JLabel(new ImageIcon(ico));
        this.add(label);
        setBounds(xPos,yPos,160,220);
    }
}
