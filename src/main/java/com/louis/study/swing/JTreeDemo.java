package com.louis.study.swing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class JTreeDemo {
    JFrame jf = new JFrame("�ɱ༭�ڵ����");
    JTree jTree;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("�ձ���Ա");
    DefaultMutableTreeNode ip = new DefaultMutableTreeNode("ip��");
    DefaultMutableTreeNode tao = new DefaultMutableTreeNode("����ľ����");
    DefaultMutableTreeNode feng = new DefaultMutableTreeNode("�㻨��");
    DefaultMutableTreeNode ssni = new DefaultMutableTreeNode("SSNI");
    DefaultMutableTreeNode you = new DefaultMutableTreeNode("��������");
    DefaultMutableTreeNode hua = new DefaultMutableTreeNode("�ӱ��˻�");
    DefaultMutableTreeNode medi = new DefaultMutableTreeNode("MEDI");
    DefaultMutableTreeNode nan = new DefaultMutableTreeNode("������");
    DefaultMutableTreeNode qiao = new DefaultMutableTreeNode("����ʥ��");

    //JTree�����Ӧ��model
    DefaultTreeModel model;
    //������Ҫ���϶���Treepath
    TreePath movePath;
    JButton addSiblingButton = new JButton("����ֵܽڵ�");
    JButton addChildButton = new JButton("����ӽڵ�");
    JButton deleteButton = new JButton("ɾ���ڵ�");
    JButton editButton = new JButton("�༭��ǰ�ڵ�");

    public  void init(){
        root.add(ip);
        root.add(ssni);
        root.add(medi);
        ip.add(tao);
        ip.add(feng);
        ssni.add(you);
        ssni.add(hua);
        medi.add(nan);
        medi.add(qiao);
        jTree = new JTree(root);

        //��ȡ��Ӧ��TreeModel����
        model = (DefaultTreeModel)jTree.getModel();
        jTree.setEditable(true);
        jTree.setDragEnabled(true);

        MouseListener m1 = new MouseAdapter() {
            //�������ʱ��ñ��϶��Ľڵ�
            @Override
            public void mousePressed(MouseEvent e) {
                //�����ҪΨһȷ��ĳ���ڵ㣬�����ͨ��TreePath����ȡ
                TreePath tp = jTree.getPathForLocation(e.getX(),e.getY());
                if(tp!=null){
                    movePath = tp;
                }
            }

            //�ɿ����ʱ�����Ҫ�ϵ��ĸ����ڵ�
            @Override
            public void mouseReleased(MouseEvent e) {
                //��������ɿ�ʱ��TreePath����ȡTreePath
                TreePath tp = jTree.getPathForLocation(e.getX(),e.getY());
                if(tp!=null && movePath!=null){
                    //�������ɿ�ʱѡ�еĽڵ���movepath���ӽڵ㣬�����ƶ�
                    if(movePath.isDescendant(tp) && movePath != tp){
                        JOptionPane.showMessageDialog(jf,"Ŀ��ڵ��Ǳ��ƶ��ڵ���ӽڵ��޷��ƶ�","�Ƿ�����",JOptionPane.ERROR_MESSAGE);
                        return;
                    }else if(movePath != tp){
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tp.getLastPathComponent();
                        System.out.println(node);
                        ((DefaultMutableTreeNode)tp.getLastPathComponent()).add((DefaultMutableTreeNode)movePath.getLastPathComponent());
                        movePath = null;
                        jTree.updateUI();
                    }
                }
            }
        };
        jTree.addMouseListener(m1);

        jf.add(new JScrollPane(jTree));
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new JTreeDemo().init();
    }
}
