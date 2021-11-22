package com.louis.study.swing;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

/**
 * @��С�D
 * @www.louis_lai.com
 */
//ͼƬ�����
public class ImageViewer {

    //����ͼƬԤ�������С
    final int PREVIEW_SIZE = 100;
    JFrame jf = new JFrame("��ͼƬ�鿴��");
    JMenuBar menuBar = new JMenuBar();

    //��Label������ʾͼƬ
    JLabel label = new JLabel();
    JFileChooser chooser = new JFileChooser("D:\\Codes\\JavaCode\\TeaIdentify\\src\\main\\resources\\images\\girl");
    JLabel accessory = new JLabel();
    //�����ļ�������
    ExtensionFileFilter filter = new ExtensionFileFilter();

    public void init(){
        filter.addExtension("jpg");
        filter.addExtension("jpeg");
        filter.addExtension("gif");
        filter.addExtension("png");
        filter.setDescription("ͼƬ�ļ�(*.jpg,*.jpeg,*.gif,*.png)");
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        //Ϊ�ļ�ѡ����ָ���Զ����FileView����
        chooser.setFileView(new FileIconView(filter));
        //Ϊ�ļ���ѡ����ָ��һ��Ԥ��ͼƬ�ĸ���
        chooser.setAccessory(accessory);
        //����Ԥ��ͼƬ����Ĵ�С�ͱ߿�
        accessory.setPreferredSize(new Dimension(PREVIEW_SIZE,PREVIEW_SIZE));
        accessory.setBorder(BorderFactory.createEtchedBorder());
        //���ڼ�ⱻѡ���ļ��ĸı��¼�
        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //JFileChooser�ı�ѡ�ļ��Ѿ������˸ı�
                if(evt.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
                    //��ȡ�û�ѡ������ļ�
                    File f = (File)evt.getNewValue();
                    if(f == null){
                        accessory.setIcon(null);
                        return ;
                    }
                    //�������ļ�����ImageIcon������
                    ImageIcon icon = new ImageIcon(f.getPath());
                    //���ͼ��̫������С��
                    if(icon.getIconWidth() > PREVIEW_SIZE){
                        icon = new ImageIcon(icon.getImage().getScaledInstance(PREVIEW_SIZE,-1,Image.SCALE_DEFAULT));
                    }
                    accessory.setIcon(icon);
                }
            }
        });

        JMenu menu = new JMenu("�ļ�");
        menuBar.add(menu);
        JMenuItem openItem = new JMenuItem("��");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ʾ�ļ��Ի���
                int result = chooser.showDialog(jf,"��ͼƬ�ļ�");
                if(result == JFileChooser.APPROVE_OPTION){
                    String name = chooser.getSelectedFile().getPath();
                    //ѡȡ���ļ�
                    ImageIcon icon = new ImageIcon(name);
                    if(icon.getIconWidth()>label.getWidth()){
                        icon = new ImageIcon(icon.getImage().getScaledInstance(label.getWidth(),-1,Image.SCALE_DEFAULT));
                    }
                    label.setIcon(icon);
                }
            }
        });
        JMenuItem exitItem = new JMenuItem("�˳�");
        menu.add(exitItem);
        //Ϊ�˳��˵����¼�������
        exitItem.addActionListener(e -> System.exit(0));
        label.setPreferredSize(new Dimension(400,400));
        jf.setJMenuBar(menuBar);
        jf.add(new JScrollPane(label));
        jf.setPreferredSize(new Dimension(400,400));
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        new ImageViewer().init();
    }

    class ExtensionFileFilter extends FileFilter{
        private String description;
        private ArrayList<String> extensions = new ArrayList<>();

        //�Զ��巽������������ļ���չ��
        public void addExtension(String extension){
            if(!extension.startsWith(".")){
                extension = "."+extension;
                extensions.add(extension.toLowerCase());
            }
        }

        //��ȡ�ļ����������������ı�
        public void setDescription(String description){
            this.description = description;
        }

        @Override
        public boolean accept(File f) {
            //������ļ���·��������ܸ��ļ�
            if(f.isDirectory()) return true;
            //���ļ���תΪСд
            String name = f.getName().toLowerCase();
            //�������пɽ��ܵ���չ���������չ����ͬ�����ļ��Ϳɽ���
            for(String extension:extensions){
                if(name.endsWith(extension)){
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    //�Զ���һ��FileView�࣬����Ϊָ�����͵��ļ�������ͼ��
    class FileIconView extends FileView{
        private FileFilter filter;
        public FileIconView(FileFilter filter){
            this.filter = filter;
        }
        //Ϊ�ļ��У��ļ�����ͼ��

        @Override
        public Icon getIcon(File f) {
            if(!f.isDirectory() && filter.accept(f)){
                return new ImageIcon("src/main/resources/images/component/copy.png");
            }else if(f.isDirectory()){
                //�����Ŀ¼
                File[] files = File.listRoots();
                for(File temp : files){
                    //�����·���Ǹ�·��
                    if(temp.equals(f)){
                        return new ImageIcon("src/main/resources/images/component/new.png");
                    }
                }
                return new ImageIcon("src/main/resources/images/component/open.png");
            }
            return null;
        }
    }
}
