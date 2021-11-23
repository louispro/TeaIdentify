package com.louis.study.swing;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditTree {

    JFrame jf = new JFrame("�ɱ༭������");


    //���弸����ʼ���
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("�й�");
    DefaultMutableTreeNode guangdong = new DefaultMutableTreeNode("�㶫");
    DefaultMutableTreeNode guangxi = new DefaultMutableTreeNode("����");
    DefaultMutableTreeNode foshan = new DefaultMutableTreeNode("��ɽ");
    DefaultMutableTreeNode shantou = new DefaultMutableTreeNode("��ͷ");
    DefaultMutableTreeNode guilin = new DefaultMutableTreeNode("����");
    DefaultMutableTreeNode nanning = new DefaultMutableTreeNode("����");


    //���尴ť����ɲ���
    JButton addSiblingBtn = new JButton("����ֵܽ��");
    JButton addChildBtn = new JButton("����ӽ��");
    JButton deleteBtn = new JButton("ɾ�����");
    JButton editBtn = new JButton("�༭��ǰ���");

    public void init() {

        //ͨ��add()�����������Ӳ㼶��ϵ
        guangdong.add(foshan);
        guangdong.add(shantou);
        guangxi.add(guilin);
        guangxi.add(nanning);
        root.add(guangdong);
        root.add(guangxi);

        JTree tree = new JTree(root);

        //�����Ľ��༭�Ĵ���
        tree.setEditable(true);
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        //�������
        addSiblingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //����ֵܽ���߼�

                //1.��ȡ��ǰѡ�еĽ��
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) {
                    return;
                }

                //2.��ȡ��ǰ���ĸ����
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
                if (parentNode == null) {
                    return;
                }

                //3.�����½��
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("�½��");

                //4.���½��ͨ�������������
                int index = parentNode.getIndex(selectedNode);
                model.insertNodeInto(newNode, parentNode, index);

                //5.��ʾ�½��
                TreeNode[] pathToRoot = model.getPathToRoot(newNode);
                TreePath treePath = new TreePath(pathToRoot);
                tree.scrollPathToVisible(treePath);

                //6.�ػ�tree
                tree.updateUI();

            }
        });

        addChildBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ϊѡ�н������ӽڵ�

                //1.��ȡѡ�н��
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) {
                    return;
                }
                //2.�����½��
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("�½��");

                //3.���½����ӵ���ǰ�����
                selectedNode.add(newNode);

                //4.��ʾ�½��
                TreeNode[] pathToRoot = model.getPathToRoot(newNode);
                TreePath treePath = new TreePath(pathToRoot);
                tree.scrollPathToVisible(treePath);

                //5.�ػ�UI
                tree.updateUI();
            }
        });

        //����ɾ��
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                if (selectedNode != null && selectedNode.getParent() != null) {
                    model.removeNodeFromParent(selectedNode);
                }

            }
        });

        //����༭
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ȡ��ǰѡ�н���·��
                TreePath selectionPath = tree.getSelectionPath();

                //�ж����·����Ϊ�գ������ø�·�������һ�����ɱ༭
                if (selectionPath != null) {
                    tree.startEditingAtPath(selectionPath);
                }
            }
        });

        jf.add(new JScrollPane(tree));

        JPanel panel = new JPanel();
        panel.add(addSiblingBtn);
        panel.add(addChildBtn);
        panel.add(deleteBtn);
        panel.add(editBtn);


        jf.add(panel, BorderLayout.SOUTH);


        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new EditTree().init();
    }
}
