package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.render.SystemTreeRender;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import java.awt.*;

/**
 * 赖小燚
 * www.louis.com
 */
public class SystemTree extends JTree{

    public static DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统功能");;
    private DefaultMutableTreeNode labelImage;
    private DefaultMutableTreeNode processImage;
    private DefaultMutableTreeNode trainModel;

    public DefaultMutableTreeNode getLabelImage() {
        return labelImage;
    }

    public DefaultMutableTreeNode getProcessImage() {
        return processImage;
    }

    public DefaultMutableTreeNode getTrainModel() {
        return trainModel;
    }

    public DefaultMutableTreeNode getTeaBudIdentify() {
        return teaBudIdentify;
    }

    private DefaultMutableTreeNode teaBudIdentify;

    public SystemTree(TreeNode root) {
        super(root);
        labelImage = new DefaultMutableTreeNode("图像标注");
        processImage = new DefaultMutableTreeNode("图像处理");
        trainModel = new DefaultMutableTreeNode("模型训练");
        teaBudIdentify = new DefaultMutableTreeNode("嫩芽识别");
    }

    public void init(){
        root.add(labelImage);
        root.add(processImage);
        root.add(trainModel);
        root.add(teaBudIdentify);
        //展开所有节点
        for(int i = 0;i < this.getRowCount();i++){
            this.expandRow(i);
        }
        //设置自定义风格
        SystemTreeRender systemTreeRender = new SystemTreeRender();
        systemTreeRender.setBackgroundNonSelectionColor(new Color(203,220,217));
        systemTreeRender.setBackgroundSelectionColor(new Color(30,144,255));
        setCellRenderer(systemTreeRender);
        //设置默认选中嫩芽识别
        setSelectionRow(4);
        setBackground(new Color(203,220,217));


    }


    public static void main(String[] args) {
        JFrame jFrame  = new JFrame("树");
        SystemTree systemTree = new SystemTree(SystemTree.root);
        systemTree.init();
        jFrame.add(systemTree);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(300,300);
    }
}
