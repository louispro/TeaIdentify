package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class BindKeyDemo {
    JFrame jFrame = new JFrame("���̰�");
    JTextArea jTextArea = new JTextArea(5,30);
    JButton jButton = new JButton("����");
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
        //ΪjTextField�󶨼��̽ӿ�ݼ�
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
