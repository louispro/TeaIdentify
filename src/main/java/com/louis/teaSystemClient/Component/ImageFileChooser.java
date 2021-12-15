package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.filter.ImageFileFilter;
import com.louis.teaSystemClient.render.FileIconView;

import javax.swing.*;
import java.awt.*;

/**
 * 赖小燚
 * www.louis.com
 */
public class ImageFileChooser extends JFileChooser {

    //文件过滤器
    private ImageFileFilter imageFileFilter = new ImageFileFilter();
    //自定义文件试图对象
    private FileIconView fileIconView = new FileIconView(imageFileFilter);
    //预览图片
    private JLabel accessory = new JLabel();
    //图片预览组件的大小
    private int PREVIEW_SIZE = 200;

    @Override
    public JLabel getAccessory() {
        return accessory;
    }

    public int getPREVIEW_SIZE() {
        return PREVIEW_SIZE;
    }

    public ImageFileChooser(String currentDirectoryPath) {
        super(currentDirectoryPath);
        imageFileFilter.addExtension("jpg");
        imageFileFilter.addExtension("png");
        imageFileFilter.addExtension("jpeg");
        imageFileFilter.addExtension("gif");
        imageFileFilter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");
        //添加文件过滤器
        this.addChoosableFileFilter(imageFileFilter);
        //禁止文件类型下拉列表中显示所有文件选项
        this.setAcceptAllFileFilterUsed(false);
        //为文件选择器自定义FileView对象
        this.setFileView(fileIconView);
        //设置文件框标题
        this.setDialogTitle("选择图片");
        //为文件选择器指定一个预览图片的附件
        this.setAccessory(accessory);
        accessory.setPreferredSize(new Dimension(200,200));
        accessory.setBorder(BorderFactory.createEtchedBorder());
    }
}
