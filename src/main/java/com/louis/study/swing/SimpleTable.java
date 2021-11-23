package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class SimpleTable {
    JFrame jf = new JFrame("简单表格");
    JTable table;
    //定义二维数组作为表格数据
    Object[][] tableData = {
            new Object[]{"李清照",29,"女"},
            new Object[]{"苏格拉底",56,"男"},
            new Object[]{"李白",35,"男"},
            new Object[]{"桃乃木香奈",26,"女"},
            new Object[]{"枫花恋",24,"女"},
            new Object[]{"河北彩花",24,"女"}
    };
    //定义以为数据作为列标题
    Object[] columnTitle = new Object[]{"姓名","年龄","性别"};

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
