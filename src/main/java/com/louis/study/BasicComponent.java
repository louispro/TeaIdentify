package com.louis.study;

import javax.swing.*;
import java.awt.*;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class BasicComponent {
    //�������
    Frame frame = new Frame("�������");

    TextArea ta = new TextArea(5,20);

    Choice colorChooser = new Choice();

    CheckboxGroup checkboxGroup = new CheckboxGroup();
    Checkbox male = new Checkbox("��",true,checkboxGroup);
    Checkbox female = new Checkbox("Ů",false,checkboxGroup);

    Checkbox married = new Checkbox("�Ƿ��ѻ�");

    TextField textField = new TextField(20);
    Button ok = new Button("ȷ��");

    List colorList = new List(6,true);

    public void init(){
        //��װ����

        //��װ�ײ�
        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(textField);
        bottomBox.add(ok);

        frame.add(bottomBox,BorderLayout.SOUTH);

        //��װѡ�񲿷�
        colorChooser.add("��ɫ");
        colorChooser.add("��ɫ");
        colorChooser.add("��ɫ");

        Box choiceBox = Box.createHorizontalBox();
        choiceBox.add(colorChooser);
        choiceBox.add(male);
        choiceBox.add(female);
        choiceBox.add(married);



        //��װ�ı����ѡ�񲿷�
        Box topLeft = Box.createVerticalBox();
        topLeft.add(ta);
        topLeft.add(choiceBox);

        //��װ������ߺ��б��
        colorList.add("��ɫ");
        colorList.add("��ɫ");
        colorList.add("��ɫ");

        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        top.add(colorList);

        frame.add(top);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BasicComponent basicComponent = new BasicComponent();
        basicComponent.init();
    }
}
