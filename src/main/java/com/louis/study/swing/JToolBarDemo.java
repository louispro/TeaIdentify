package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JToolBarDemo {

    JFrame jFrame = new JFrame("测试工具条");
    JTextArea jTextArea = new JTextArea(6,35);
    JToolBar jToolBar = new JToolBar();
    JMenuBar jMenuBar = new JMenuBar();
    JMenu edit = new JMenu("编辑");

    //获取系统剪切板
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    //粘贴动作
    Action pasteAction = new AbstractAction("粘贴",new ImageIcon("src/main/resources/images/component/paste.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
                try{
                    //取出剪贴板中的内容
                    String content = (String)clipboard.getData(DataFlavor.stringFlavor);
                    jTextArea.replaceRange(content,jTextArea.getSelectionStart(),jTextArea.getSelectionEnd());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    };

    //复制动作
    Action copyAction = new AbstractAction("复制",new ImageIcon("src/main/resources/images/component/copy.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            //获取选中的文本
            StringSelection contents = new StringSelection(jTextArea.getSelectedText());
            //将选中的文本放入剪贴板中
            clipboard.setContents(contents,null);
            //如果剪贴板中包含文本则激活粘贴动作
            if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
                pasteAction.setEnabled(true);
            }
        }
    };

    public void init(){
        pasteAction.setEnabled(false);  //粘贴动作默认不激活
        jFrame.add(new JScrollPane((jTextArea)));

        //以Action创建按钮
        JButton copyBtn = new JButton(copyAction);
        JButton pasteBtn = new JButton(pasteAction);

        JPanel jPanel = new JPanel();
        jPanel.add(copyBtn);
        jPanel.add(pasteBtn);

        //向工具条中添加Action对象，该对象将会转换为工具按钮
        jToolBar.add(copyAction);
        jToolBar.add(pasteAction);

        //向菜单栏中添加action对象，该对象将会换成菜单项
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
