package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.listener.FailListener;
import com.louis.teaSystemClient.listener.SuccessListener;
import com.louis.teaSystemClient.pojo.ResultInfo;
import com.louis.teaSystemClient.util.GetUtils;
import com.louis.teaSystemClient.util.JsonUtils;
import com.louis.teaSystemService.pojo.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaBudIdentifyBox extends JPanel{
//    final int Width = 850;
//    final int HEIGHT = 600;
    JComboBox<String> modelCombox = new JComboBox<>();
//    JLabel originImageLabel = new JLabel();
//    JLabel identifyImageLabel = new JLabel();
//
//    /**
//     * Creates a <code>Box</code> that displays its components
//     * along the specified axis.
//     * @throws AWTError if the <code>axis</code> is invalid
//     * @see #createHorizontalBox
//     * @see #createVerticalBox
//     */
    public TeaBudIdentifyBox() {
        //垂直布局
        this.setLayout(new BorderLayout());
        this.loadModel();
        JPanel modelPanel = new JPanel();
        modelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        modelPanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
        modelPanel.add(modelCombox);
        this.add(modelPanel,BorderLayout.NORTH);

        //组装试图
//        //按钮panel
//        JPanel btnPanel = new JPanel();
//        Color color = new Color(203,220,217);
//        btnPanel.setBackground(color);
//        btnPanel.setMaximumSize((new Dimension(850,35)));
//        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        model.addItem("Fast RCNN");
//        model.addItem("YOLO");
//        //下拉列表
//        btnPanel.add(model);
//
//        //识别box
//        Box identifyBox = Box.createHorizontalBox();
//        originImageLabel.setBorder(BorderFactory.createLineBorder(Color.gray,5));
//        identifyImageLabel.setBorder(BorderFactory.createLineBorder(Color.gray,5));
//        identifyBox.add(originImageLabel);
//        identifyBox.add(identifyImageLabel);
//
//        //组装
//        this.add(btnPanel);
//        this.add(identifyBox);
    }

    public void loadModel(){
//        Box parent = this;
        GetUtils.get("http://localhost:8080/model/getModels", new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                if(resultInfo.isFlag()){
                    List<Model> modelList = (List<Model>)resultInfo.getData();
                    Iterator<Model> iterator = modelList.iterator();
                    while (iterator.hasNext()){
                        Model model = iterator.next();
                        modelCombox.addItem(model.getModelName());
                    }
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
//                JOptionPane.showMessageDialog(parent,"网络异常，请检查网络");
            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("嫩芽识别");
        TeaBudIdentifyBox teaBudIdentifyBox = new TeaBudIdentifyBox();
        jFrame.add(teaBudIdentifyBox);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(850,500);
    }
}
