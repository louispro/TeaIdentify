package com.louis.study.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class JTabbedPaneDemo {

    JFrame jf = new JFrame("����Tabҳ��");
    JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);
    ImageIcon icon = new ImageIcon("src/main/resources/images/component/close.gif");
    String[] layouts = {"���в���","��������"};
    String[] positions = {"���","����","�ұ�","�ײ�"};
    Map<String,String> huoying = new LinkedHashMap<>();
    public void init(){
        huoying.put("���ǲ���","src/main/resources/images/book/you.png");
        huoying.put("���ǲ�����","src/main/resources/images/book/sasiki.png");
        huoying.put("��������","src/main/resources/images/book/naruto.png");
        String tip = "��Ƭ";
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
        //ϵͳĬ�ϼ��ص�һҳ
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

    //����ָ����ǩҳ����
    private void loadTab(int n){
        String title = jTabbedPane.getTitleAt(n);
        //���ݱ�ǩҳ�ı����ȡ��Ӧ��ͼƬ
        ImageIcon bookImage = new ImageIcon(huoying.get(title));
        jTabbedPane.setComponentAt(n,new JLabel(bookImage));
        jTabbedPane.setIconAt(n,new ImageIcon("src/main/resources/images/component/open.gif"));
    }
}
