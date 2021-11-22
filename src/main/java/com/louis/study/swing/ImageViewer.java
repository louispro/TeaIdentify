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
 * @赖小燚
 * @www.louis_lai.com
 */
//图片浏览器
public class ImageViewer {

    //定义图片预览组件大小
    final int PREVIEW_SIZE = 100;
    JFrame jf = new JFrame("简单图片查看器");
    JMenuBar menuBar = new JMenuBar();

    //该Label用于显示图片
    JLabel label = new JLabel();
    JFileChooser chooser = new JFileChooser("D:\\Codes\\JavaCode\\TeaIdentify\\src\\main\\resources\\images\\girl");
    JLabel accessory = new JLabel();
    //定义文件过滤器
    ExtensionFileFilter filter = new ExtensionFileFilter();

    public void init(){
        filter.addExtension("jpg");
        filter.addExtension("jpeg");
        filter.addExtension("gif");
        filter.addExtension("png");
        filter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        //为文件选择器指定自定义的FileView对象
        chooser.setFileView(new FileIconView(filter));
        //为文件按选择器指定一个预览图片的附件
        chooser.setAccessory(accessory);
        //设置预览图片组件的大小和边框
        accessory.setPreferredSize(new Dimension(PREVIEW_SIZE,PREVIEW_SIZE));
        accessory.setBorder(BorderFactory.createEtchedBorder());
        //用于检测被选择文件的改变事件
        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //JFileChooser的被选文件已经发生了改变
                if(evt.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
                    //获取用户选择的新文件
                    File f = (File)evt.getNewValue();
                    if(f == null){
                        accessory.setIcon(null);
                        return ;
                    }
                    //将所有文件读入ImageIcon对象中
                    ImageIcon icon = new ImageIcon(f.getPath());
                    //如果图像太大，则缩小它
                    if(icon.getIconWidth() > PREVIEW_SIZE){
                        icon = new ImageIcon(icon.getImage().getScaledInstance(PREVIEW_SIZE,-1,Image.SCALE_DEFAULT));
                    }
                    accessory.setIcon(icon);
                }
            }
        });

        JMenu menu = new JMenu("文件");
        menuBar.add(menu);
        JMenuItem openItem = new JMenuItem("打开");
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //显示文件对话框
                int result = chooser.showDialog(jf,"打开图片文件");
                if(result == JFileChooser.APPROVE_OPTION){
                    String name = chooser.getSelectedFile().getPath();
                    //选取的文件
                    ImageIcon icon = new ImageIcon(name);
                    if(icon.getIconWidth()>label.getWidth()){
                        icon = new ImageIcon(icon.getImage().getScaledInstance(label.getWidth(),-1,Image.SCALE_DEFAULT));
                    }
                    label.setIcon(icon);
                }
            }
        });
        JMenuItem exitItem = new JMenuItem("退出");
        menu.add(exitItem);
        //为退出菜单绑定事件监听器
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

        //自定义方法，用于添加文件扩展名
        public void addExtension(String extension){
            if(!extension.startsWith(".")){
                extension = "."+extension;
                extensions.add(extension.toLowerCase());
            }
        }

        //获取文件过滤器的描述性文本
        public void setDescription(String description){
            this.description = description;
        }

        @Override
        public boolean accept(File f) {
            //如果该文件是路径，则接受该文件
            if(f.isDirectory()) return true;
            //将文件名转为小写
            String name = f.getName().toLowerCase();
            //遍历所有可接受的扩展名，如果扩展名相同，该文件就可接受
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

    //自定义一个FileView类，用于为指定类型的文件夹设置图标
    class FileIconView extends FileView{
        private FileFilter filter;
        public FileIconView(FileFilter filter){
            this.filter = filter;
        }
        //为文件夹，文件设置图标

        @Override
        public Icon getIcon(File f) {
            if(!f.isDirectory() && filter.accept(f)){
                return new ImageIcon("src/main/resources/images/component/copy.png");
            }else if(f.isDirectory()){
                //如果是目录
                File[] files = File.listRoots();
                for(File temp : files){
                    //如果该路径是根路径
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
