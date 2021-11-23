package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class JProgressBarDemo {
    JFrame jf = new JFrame("���Խ�����");
    JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL);
    JCheckBox indeterminate = new JCheckBox("��ȷ������");
    JCheckBox noBorder = new JCheckBox("����ֻ�߿�");

    public void init(){
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(indeterminate);
        box.add(noBorder);
        jf.setLayout(new FlowLayout());
        jf.add(box);
        //�ѽ�������ӵ�JFrame������
        jf.add(bar);
        //���ý����������ֵ����Сֵ
        bar.setMinimum(0);
        bar.setMaximum(100);
        //�����ڽ������л�����ɰٷֱ�
        bar.setStringPainted(true);
        //���ݸ�ѡ�������Ƿ���ƽ������߿�
        noBorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bar.setBorderPainted(!noBorder.isSelected());
            }
        });
        indeterminate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bar.setIndeterminate((indeterminate.isSelected()));
                bar.setStringPainted(!indeterminate.isSelected());
            }
        });
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

        //����ѭ����ʽ�����ϸı����������ɽ���
        for(int i = 0;i <= 100;i++){
            bar.setValue(i);
            try {
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new JProgressBarDemo().init();
    }
}
