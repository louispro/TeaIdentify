package com.louis.study.swing;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.*;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class SwingComponentDemo {
    JFrame jFrame = new JFrame("Swing�������");

    JMenuBar jMenuBar = new JMenuBar();

    JMenu file = new JMenu("�ļ�");
    JMenu edit = new JMenu("�༭");
    JMenu format = new JMenu("��ʽ");


    JMenuItem create = new JMenuItem("�½�");
    JMenuItem open = new JMenuItem("��");
    JMenuItem save = new JMenuItem("����");
    JMenuItem exit = new JMenuItem("�˳�");
    JMenuItem auto = new JMenuItem("�Զ�����");
    JMenuItem copy = new JMenuItem("����");
    JMenuItem paste = new JMenuItem("ճ��");
    JMenuItem comment = new JMenuItem("ע��(CTRL+/)");
    JMenuItem delComment = new JMenuItem("ȡ��ע��");


    //�����ı���
    JTextArea textArea = new JTextArea(8,20);

    //�����б��
    String[] colors = {"��ɫ","��ɫ","��ɫ"};
    JList<String> colorList = new JList<>(colors);

    //����ѡ��������
    JComboBox<String> colorSelect = new JComboBox<>();

    ButtonGroup bg = new ButtonGroup();
    JRadioButton male = new JRadioButton("��",true);
    JRadioButton female = new JRadioButton("Ů",false);

    JCheckBox married = new JCheckBox("�Ƿ��ѻ�",true);

    //�����ײ�
    JTextField textField = new JTextField(40);
    JButton ok = new JButton("ȷ��");

    //�Ҽ��˵�
    ButtonGroup popupBg = new ButtonGroup();
    JPopupMenu jPopupMenu = new JPopupMenu();
    JRadioButtonMenuItem metal = new JRadioButtonMenuItem("metal ���");
    JRadioButtonMenuItem nimbus = new JRadioButtonMenuItem("nimbus ���");
    JRadioButtonMenuItem windows = new JRadioButtonMenuItem("windows ���");
    JRadioButtonMenuItem windowsClassic = new JRadioButtonMenuItem("windowsClassic ���");
    JRadioButtonMenuItem motif = new JRadioButtonMenuItem("motif ���");


    public void init(){
        //��װ�ײ�
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(textField);
        bottomPanel.add(ok);
        jFrame.add(bottomPanel, BorderLayout.SOUTH);

        //��װѡ��������
        JPanel selectPanel = new JPanel();
        colorSelect.addItem("��ɫ");
        colorSelect.addItem("��ɫ");
        colorSelect.addItem("��ɫ");

        bg.add(male);
        bg.add(female);

        selectPanel.add(colorSelect);
        selectPanel.add(male);
        selectPanel.add(female);
        selectPanel.add(married);

        //��װ�ı����ѡ��������
        Box topLeft = Box.createVerticalBox();
        topLeft.add(textArea);
        topLeft.add(selectPanel);

        //��װ����
        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        top.add(colorList);

        //��װ�˵�
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

        //��װ�Ҽ��˵�
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
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���
        jFrame.setJMenuBar(jMenuBar); 
        jFrame.add(top);
        jFrame.pack();
        jFrame.setVisible(true);

        //ע���¼�
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

    //�л����
    private void changeStyle(String command) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        switch(command){
            case "metal ���":
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                break;
            case "nimbus ���":
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                break;
            case "windows ���":
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                break;
            case "windowsClassic ���":
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                break;
            case "motif ���":
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                break;
        }
        //ˢ��������
        SwingUtilities.updateComponentTreeUI(jFrame.getContentPane());
        SwingUtilities.updateComponentTreeUI(jMenuBar);
        SwingUtilities.updateComponentTreeUI(jPopupMenu);
    }

    public static void main(String[] args) {
        SwingComponentDemo swingComponentDemo = new SwingComponentDemo();
        swingComponentDemo.init();
    }
}
