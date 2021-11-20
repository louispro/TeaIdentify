package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class BindKeyDemo {
    JFrame jFrame = new JFrame("键盘绑定");
    JTextArea jTextArea = new JTextArea(5,30);
    JButton jButton = new JButton("发送");
    JTextField jTextField = new JTextField(15);



    public void init(){
        jFrame.add(jTextArea);
        JPanel jp = new JPanel();
        jp.add(jTextField);
        jp.add(jButton);
        jFrame.add(jp, BorderLayout.SOUTH);

        Action sendMsg = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextArea.append(jTextField.getText()+"\n");
                jTextField.setText("");
            }
        };
        //为jTextField绑定键盘接快捷键
        jTextField.getInputMap().put(KeyStroke.getKeyStroke('\n', InputEvent.CTRL_MASK),"send");
        jTextField.getActionMap().put("send",sendMsg);
        jButton.addActionListener(sendMsg);

        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new BindKeyDemo().init();
    }
}
