package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class SimpleTable {
    JFrame jf = new JFrame("�򵥱��");
    JTable table;
    //�����ά������Ϊ�������
    Object[][] tableData = {
            new Object[]{"������",29,"Ů"},
            new Object[]{"�ո�����",56,"��"},
            new Object[]{"���",35,"��"},
            new Object[]{"����ľ����",26,"Ů"},
            new Object[]{"�㻨��",24,"Ů"},
            new Object[]{"�ӱ��ʻ�",24,"Ů"}
    };
    //������Ϊ������Ϊ�б���
    Object[] columnTitle = new Object[]{"����","����","�Ա�"};

    public void init(){
        table = new JTable(tableData,columnTitle);
        jf.add(new JScrollPane(table));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleTable().init();
    }
}
