package com.louis.study.swing;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JTreeDemo {
    JFrame jf = new JFrame("可编辑节点的树");
    JTree jTree;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("日本演员");
    DefaultMutableTreeNode ip = new DefaultMutableTreeNode("ip社");
    DefaultMutableTreeNode tao = new DefaultMutableTreeNode("桃乃木香奈");
    DefaultMutableTreeNode feng = new DefaultMutableTreeNode("枫花恋");
    DefaultMutableTreeNode ssni = new DefaultMutableTreeNode("SSNI");
    DefaultMutableTreeNode you = new DefaultMutableTreeNode("三上悠亚");
    DefaultMutableTreeNode hua = new DefaultMutableTreeNode("河北菜花");
    DefaultMutableTreeNode medi = new DefaultMutableTreeNode("MEDI");
    DefaultMutableTreeNode nan = new DefaultMutableTreeNode("初川南");
    DefaultMutableTreeNode qiao = new DefaultMutableTreeNode("高桥圣子");

    //JTree对象对应的model
    DefaultTreeModel model;
    //定义需要被拖动的Treepath
    TreePath movePath;
    JButton addSiblingButton = new JButton("添加兄弟节点");
    JButton addChildButton = new JButton("添加子节点");
    JButton deleteButton = new JButton("删除节点");
    JButton editButton = new JButton("编辑当前节点");

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

        //获取对应的TreeModel对象
        model = (DefaultTreeModel)jTree.getModel();
        jTree.setEditable(true);
        jTree.setDragEnabled(true);

        MouseListener m1 = new MouseAdapter() {
            //按下鼠标时获得被拖动的节点
            @Override
            public void mousePressed(MouseEvent e) {
                //如果需要唯一确定某个节点，则必须通过TreePath来获取
                TreePath tp = jTree.getPathForLocation(e.getX(),e.getY());
                if(tp!=null){
                    movePath = tp;
                }
            }

            //松开鼠标时获得需要拖到哪个父节点
            @Override
            public void mouseReleased(MouseEvent e) {
                //根据鼠标松开时的TreePath来获取TreePath
                TreePath tp = jTree.getPathForLocation(e.getX(),e.getY());
                if(tp!=null && movePath!=null){
                    //如果鼠标松开时选中的节点是movepath的子节点，则不能移动
                    if(movePath.isDescendant(tp) && movePath != tp){
                        JOptionPane.showMessageDialog(jf,"目标节点是被移动节点的子节点无法移动","非法操作",JOptionPane.ERROR_MESSAGE);
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
