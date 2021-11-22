package com.louis.study.swing;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * @¿µ–°†D
 * @www.louis_lai.com
 */
public class SplitPaneDemo {
    JFrame jf = new JFrame("≤‚ ‘JSplitPane");
    JLabel bookCover = new JLabel();
    JTextArea bookDesc = new JTextArea();
    JPanel jPanel = new JPanel();

    public void init(){
        bookCover.setIcon(new ImageIcon("src/main/resources/images/book/sasiki.png"));
        JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT,bookCover,new JScrollPane(bookDesc));
        left.setContinuousLayout(true);
        left.setDividerSize(20);
        left.resetToPreferredSizes();
        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,jPanel);
        content.setContinuousLayout(true);
        jPanel.setPreferredSize(new Dimension(200,400));
        jf.add(content);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new SplitPaneDemo().init();
    }
}

class Book {
    String name;
    ImageIcon imageIcon;
    String description;

    public Book(String name,ImageIcon imageIcon,String description){
        this.name = name;
        this.imageIcon = imageIcon;
        this.description = description;
    }
}
