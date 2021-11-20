package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class JToolBarDemo {

    JFrame jFrame = new JFrame("���Թ�����");
    JTextArea jTextArea = new JTextArea(6,35);
    JToolBar jToolBar = new JToolBar();
    JMenuBar jMenuBar = new JMenuBar();
    JMenu edit = new JMenu("�༭");

    //��ȡϵͳ���а�
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    //ճ������
    Action pasteAction = new AbstractAction("ճ��",new ImageIcon("src/main/resources/images/component/paste.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
                try{
                    //ȡ���������е�����
                    String content = (String)clipboard.getData(DataFlavor.stringFlavor);
                    jTextArea.replaceRange(content,jTextArea.getSelectionStart(),jTextArea.getSelectionEnd());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    };

    //���ƶ���
    Action copyAction = new AbstractAction("����",new ImageIcon("src/main/resources/images/component/copy.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            //��ȡѡ�е��ı�
            StringSelection contents = new StringSelection(jTextArea.getSelectedText());
            //��ѡ�е��ı������������
            clipboard.setContents(contents,null);
            //����������а����ı��򼤻�ճ������
            if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
                pasteAction.setEnabled(true);
            }
        }
    };

    public void init(){
        pasteAction.setEnabled(false);  //ճ������Ĭ�ϲ�����
        jFrame.add(new JScrollPane((jTextArea)));

        //��Action������ť
        JButton copyBtn = new JButton(copyAction);
        JButton pasteBtn = new JButton(pasteAction);

        JPanel jPanel = new JPanel();
        jPanel.add(copyBtn);
        jPanel.add(pasteBtn);

        //�򹤾��������Action���󣬸ö��󽫻�ת��Ϊ���߰�ť
        jToolBar.add(copyAction);
        jToolBar.add(pasteAction);

        //��˵��������action���󣬸ö��󽫻ỻ�ɲ˵���
        edit.add(copyAction);
        edit.add(pasteAction);
        jMenuBar.add(edit);
        jFrame.setJMenuBar(jMenuBar);

        jToolBar.setMargin(new Insets(20,10,5,30));
        jFrame.add(jTextArea,BorderLayout.NORTH);
        jFrame.add(jPanel,BorderLayout.SOUTH);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new JToolBarDemo().init();
    }
}
