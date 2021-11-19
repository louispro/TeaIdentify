package com.louis.study;

import javax.swing.*;
import java.awt.*;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class BasicComponent {
    //基本组件
    Frame frame = new Frame("基本组件");

    TextArea ta = new TextArea(5,20);

    Choice colorChooser = new Choice();

    CheckboxGroup checkboxGroup = new CheckboxGroup();
    Checkbox male = new Checkbox("男",true,checkboxGroup);
    Checkbox female = new Checkbox("女",false,checkboxGroup);

    Checkbox married = new Checkbox("是否已婚");

    TextField textField = new TextField(20);
    Button ok = new Button("确定");

    List colorList = new List(6,true);

    public void init(){
        //组装界面

        //组装底部
        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(textField);
        bottomBox.add(ok);

        frame.add(bottomBox,BorderLayout.SOUTH);

        //组装选择部分
        colorChooser.add("红色");
        colorChooser.add("黄色");
        colorChooser.add("蓝色");

        Box choiceBox = Box.createHorizontalBox();
        choiceBox.add(colorChooser);
        choiceBox.add(male);
        choiceBox.add(female);
        choiceBox.add(married);



        //组装文本域和选择部分
        Box topLeft = Box.createVerticalBox();
        topLeft.add(ta);
        topLeft.add(choiceBox);

        //组装顶部左边和列表框
        colorList.add("红色");
        colorList.add("黄色");
        colorList.add("蓝色");

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
