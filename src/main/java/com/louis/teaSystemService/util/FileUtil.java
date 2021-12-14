package com.louis.teaSystemService.util;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * 赖小燚
 * www.louis.com
 */
public class FileUtil {

    public static String createFileName(String filename,String format){
        System.out.println("转换文件名开始");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String current = df.format(System.currentTimeMillis());
        String name = filename+"-"+current+"."+format;
        System.out.println(name);
        System.out.println("转换文件名结束");
        return name;
    }

    public static void copyImage(String oldPath,String newPath){
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(oldPath);
            byte[] b = new byte[in.available()];
            in.read(b);
            out = new FileOutputStream(newPath);
            out.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        createFileName("water","jpg");
    }
}
