package com.louis.teaSystemClient.filter;

/**
 * 赖小燚
 * www.louis.com
 */

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.ArrayList;

/**
 * 图片文件过滤器
 */
public class ImageFileFilter extends FileFilter {
    private String description;
    private ArrayList<String> extensions = new ArrayList<>();

    //自定义方法，用于添加文件扩展名
    public void addExtension(String extension){
        if(!extension.startsWith(".")){
            extension = "."+extension;
            extensions.add(extension.toLowerCase());
        }
    }

    //设置该过滤器的描述文本
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean accept(File f) {
        if(f.isDirectory())
            return true;
        //将文件名转为小写，
        String name = f.getName().toLowerCase();
        //遍历所有可接受的扩展名，如果扩展名相同，该文件就可接受
        for(String extension : extensions){
            if(name.endsWith(extension)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
