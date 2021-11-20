package com.louis.study.swing;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.*;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class SwingComponentDemo {
    JFrame jFrame = new JFrame("Swing基本组件");

    JMenuBar jMenuBar = new JMenuBar();

    JMenu file = new JMenu("文件");
    JMenu edit = new JMenu("编辑");
    JMenu format = new JMenu("格式");


    JMenuItem create = new JMenuItem("新建");
    JMenuItem open = new JMenuItem("打开");
    JMenuItem save = new JMenuItem("保存");
    JMenuItem exit = new JMenuItem("退出");
    JMenuItem auto = new JMenuItem("自动换行");
    JMenuItem copy = new JMenuItem("复制");
    JMenuItem paste = new JMenuItem("粘贴");
    JMenuItem comment = new JMenuItem("注释(CTRL+/)");
    JMenuItem delComment = new JMenuItem("取消注释");


    //声明文本域
    JTextArea textArea = new JTextArea(8,20);

    //声明列表框
    String[] colors = {"红色","蓝色","黄色"};
    JList<String> colorList = new JList<>(colors);

    //声明选择相关组件
    JComboBox<String> colorSelect = new JComboBox<>();

    ButtonGroup bg = new ButtonGroup();
    JRadioButton male = new JRadioButton("男",true);
    JRadioButton female = new JRadioButton("女",false);

    JCheckBox married = new JCheckBox("是否已婚",true);

    //声明底部
    JTextField textField = new JTextField(40);
    JButton ok = new JButton("确定");

    //右键菜单
    ButtonGroup popupBg = new ButtonGroup();
    JPopupMenu jPopupMenu = new JPopupMenu();
    JRadioButtonMenuItem metal = new JRadioButtonMenuItem("metal 风格");
    JRadioButtonMenuItem nimbus = new JRadioButtonMenuItem("nimbus 风格");
    JRadioButtonMenuItem windows = new JRadioButtonMenuItem("windows 风格");
    JRadioButtonMenuItem windowsClassic = new JRadioButtonMenuItem("windowsClassic 风格");
    JRadioButtonMenuItem motif = new JRadioButtonMenuItem("motif 风格");


    public void init(){
        //组装底部
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(textField);
        bottomPanel.add(ok);
        jFrame.add(bottomPanel, BorderLayout.SOUTH);

        //组装选择相关组件
        JPanel selectPanel = new JPanel();
        colorSelect.addItem("红色");
        colorSelect.addItem("绿色");
        colorSelect.addItem("蓝色");

        bg.add(male);
        bg.add(female);

        selectPanel.add(colorSelect);
        selectPanel.add(male);
        selectPanel.add(female);
        selectPanel.add(married);

        //组装文本域和选择相关组件
        Box topLeft = Box.createVerticalBox();
        topLeft.add(textArea);
        topLeft.add(selectPanel);

        //组装顶部
        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        top.add(colorList);

        //组装菜单
        file.add(create);
        file.add(open);
        file.add(save);
        file.add(exit);

        edit.add(auto);
        edit.addSeparator();
        edit.add(copy);
        edit.add(paste);
        edit.addSeparator();
        edit.add(format);

        format.add(comment);
        format.add(delComment);

        //组装右键菜单
        popupBg.add(metal);
        popupBg.add(nimbus);
        popupBg.add(windows);
        popupBg.add(windowsClassic);
        popupBg.add(motif);


        jPopupMenu.add(metal);
        jPopupMenu.add(nimbus);
        jPopupMenu.add(windows);
        jPopupMenu.add(windowsClassic);
        jPopupMenu.add(motif);

        textArea.setComponentPopupMenu(jPopupMenu);
        jFrame.add(jPopupMenu);

        jMenuBar.add(file);
        jMenuBar.add(edit);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗口
        jFrame.setJMenuBar(jMenuBar); 
        jFrame.add(top);
        jFrame.pack();
        jFrame.setVisible(true);

        //注册事件
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand = e.getActionCommand();
                try{
                    changeStyle(actionCommand);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

        metal.addActionListener(listener);
        nimbus.addActionListener(listener);
        windows.addActionListener(listener);
        windowsClassic.addActionListener(listener);
        motif.addActionListener(listener);
    }

    //切换风格
    private void changeStyle(String command) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        switch(command){
            case "metal 风格":
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                break;
            case "nimbus 风格":
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                break;
            case "windows 风格":
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                break;
            case "windowsClassic 风格":
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                break;
            case "motif 风格":
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                break;
        }
        //刷新组件外观
        SwingUtilities.updateComponentTreeUI(jFrame.getContentPane());
        SwingUtilities.updateComponentTreeUI(jMenuBar);
        SwingUtilities.updateComponentTreeUI(jPopupMenu);
    }

    public static void main(String[] args) {
        SwingComponentDemo swingComponentDemo = new SwingComponentDemo();
        swingComponentDemo.init();
    }
}
