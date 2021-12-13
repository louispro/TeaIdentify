package com.louis.teaSystemClient.test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 赖小燚
 * www.louis.com
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器："+systemLoader);

        try {
            Enumeration<URL> eml = systemLoader.getResources("");
            while(eml.hasMoreElements()){
                System.out.println(eml.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取系统类加载器的父类加载器，得到扩展类加载器
        ClassLoader extensionLoader = systemLoader.getParent();
        System.out.println("扩展类加载器："+extensionLoader);
        System.out.println("扩展类加载器的加载路径："+System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的parent："+extensionLoader.getParent());
    }
}
