package com.louis.teaSystemClient.render;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.io.File;

/**
 * 赖小燚
 * www.louis.com
 */
public class FileIconView extends FileView {
    private FileFilter filter;

    public FileIconView(FileFilter filter){
        this.filter = filter;
    }

    @Override
    public Icon getIcon(File f) {
        if(!f.isDirectory() && filter.accept(f)){
            return new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/image.png");
        }else if(f.isDirectory()){
            //获取所有根路径
            File[] fList =File.listRoots();
            for(File temp : fList){
                //如果该路径是根路径
                if(temp.equals(f)){
                    return new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/folder.png");
                }
            }
            return new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/folder.png");
        }else {
            return null;
        }
    }
}
