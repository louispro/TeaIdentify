package com.louis.study.swing;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;


public class ListTest {
    JFrame jf = new JFrame("�б�����");
    String[] books = {"java��ѧ����","������javaEE��ҵӦ��ʵս","Android�����̳�","jQueryʵս�̳�","SpringBoot��ҵ������"};

    //���� ����ѡ��ť ���ڵ����
    JPanel layoutPanel = new JPanel();
    ButtonGroup layoutGroup = new ButtonGroup();

    //���� ѡ��ģʽ��ť �������
    JPanel selectModePanel = new JPanel();
    ButtonGroup selectModeGroup = new ButtonGroup();

    JTextArea favorite = new JTextArea(4,40);


    //��һ���ַ�������������һ��JList����
    JList<String> bookList ;
    JComboBox<String> bookSelector;

    public void init(){
        //��װ��ͼ

        //��װJlist�������
        bookList = new JList<>(books);

        addBtn2LayoutPanel("�������",JList.VERTICAL);
        addBtn2LayoutPanel("������",JList.VERTICAL_WRAP);
        addBtn2LayoutPanel("������",JList.HORIZONTAL_WRAP);

        addBtn2SelectModelPanel("������",ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addBtn2SelectModelPanel("��ѡ",ListSelectionModel.SINGLE_SELECTION);
        addBtn2SelectModelPanel("����Χ",ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        //��JList������
        bookList.setVisibleRowCount(3);
        bookList.setSelectionInterval(2,4);

        //������Ŀѡ���¼�
        bookList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //��ȡ��ǰѡ�е���Ŀ
                List<String> selectedValuesList = bookList.getSelectedValuesList();
                //�ѵ�ǰ��Ŀ���������õ��ı����С�
                favorite.setText("");
                for (String str : selectedValuesList) {
                    favorite.append(str+"\n");
                }
            }
        });

        Box bookListVBox = Box.createVerticalBox();
        bookListVBox.add(new JScrollPane(bookList));
        bookListVBox.add(layoutPanel);
        bookListVBox.add(selectModePanel);

        //��װJComboBox
        Vector<String> vector = new Vector<>();
        List<String> list = List.of("java��ѧ����", "������javaEE��ҵӦ��ʵս", "Android�����̳�", "jQueryʵս�̳�", "SpringBoot��ҵ������");
        vector.addAll(list);

        bookSelector = new JComboBox<>(vector);

        bookSelector.setEditable(true);

        bookSelector.setMaximumRowCount(4);

        bookSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //��ȡ��ǰ�Ѿ�ѡ�е���Ŀ�����������õ��ı�����
                Object selectedItem = bookSelector.getSelectedItem();
                favorite.setText(selectedItem.toString());
            }
        });

        //��װ����������������
        Box topBox = Box.createHorizontalBox();
        topBox.add(bookListVBox);
        JPanel bookSelectPanel = new JPanel();
        bookSelectPanel.add(bookSelector);
        topBox.add(bookSelectPanel);

        //��װ�ײ�
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(new JLabel("����ϲ����ͼ�飺"),BorderLayout.NORTH);
        bottomPanel.add(favorite);

        //��װ����
        Box holeBox = Box.createVerticalBox();
        holeBox.add(topBox);
        holeBox.add(bottomPanel);

        jf.add(holeBox);


        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }

    //��װ��������layoutPanel����ӵ�ѡ��ť
    public void addBtn2LayoutPanel(String name,int layoutType){
        //���ñ���߿�
        layoutPanel.setBorder(new TitledBorder(new EtchedBorder(),"ȷ��ѡ���"));

        //������ѡ��ť
        JRadioButton button = new JRadioButton(name);

        layoutPanel.add(button);

        //�õ�һ����ťĬ��ѡ��
        if (layoutGroup.getButtonCount()==0){
            button.setSelected(true);
        }

        layoutGroup.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList.setLayoutOrientation(layoutType);
            }
        });
    }

    //��װ��������selectModelPanel��Ӱ�ť
    public void addBtn2SelectModelPanel(String name,int selectionModel){
        //���ñ���߿�
        selectModePanel.setBorder(new TitledBorder(new EtchedBorder(),"ȷ��ѡ��ģʽ"));

        //������ѡ��ť
        JRadioButton button = new JRadioButton(name);

        selectModePanel.add(button);

        //�õ�һ����ťĬ��ѡ��
        if (selectModeGroup.getButtonCount()==0){
            button.setSelected(true);
        }

        selectModeGroup.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList.setSelectionMode(selectionModel);
            }
        });
    }


    public static void main(String[] args) {
        new ListTest().init();
    }
}
