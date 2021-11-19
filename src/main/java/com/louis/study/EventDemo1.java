package com.louis.study;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class EventDemo1 {

    Frame frame = new Frame("事件处理");

    TextField textField = new TextField();
    Button button = new Button("插入文本");

    public void init(){
        Box box = Box.createVerticalBox();
        box.add(textField);
        box.add(button);
        frame.add(box);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("hello event");
            }
        });

        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        EventDemo1 eventDemo1 = new EventDemo1();
        eventDemo1.init();
    }
}
