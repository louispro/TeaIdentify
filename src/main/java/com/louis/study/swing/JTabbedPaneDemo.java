package com.louis.study.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JTabbedPaneDemo {

    JFrame jf = new JFrame("测试Tab页面");
    JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);
    ImageIcon icon = new ImageIcon("src/main/resources/images/component/close.gif");
    String[] layouts = {"换行布局","滚动布局"};
    String[] positions = {"左边","顶部","右边","底部"};
    Map<String,String> huoying = new LinkedHashMap<>();
    public void init(){
        huoying.put("宇智波鼬","src/main/resources/images/book/you.png");
        huoying.put("宇智波佐助","src/main/resources/images/book/sasiki.png");
        huoying.put("漩涡鸣人","src/main/resources/images/book/naruto.png");
        String tip = "照片";
        for(String ying:huoying.keySet()){
            jTabbedPane.addTab(ying,icon,null,tip);
        }
        jf.add(jTabbedPane, BorderLayout.CENTER);
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int n = jTabbedPane.getSelectedIndex();
                loadTab(n);
            }
        });
        //系统默认加载第一页
        loadTab(0);
        jTabbedPane.setPreferredSize(new Dimension(500,300));
        jf.add(jTabbedPane);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new JTabbedPaneDemo().init();
    }

    //加载指定标签页内容
    private void loadTab(int n){
        String title = jTabbedPane.getTitleAt(n);
        //根据标签页的标题获取对应的图片
        ImageIcon bookImage = new ImageIcon(huoying.get(title));
        jTabbedPane.setComponentAt(n,new JLabel(bookImage));
        jTabbedPane.setIconAt(n,new ImageIcon("src/main/resources/images/component/open.gif"));
    }
}
