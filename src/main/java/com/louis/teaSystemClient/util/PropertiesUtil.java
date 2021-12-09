package com.louis.teaSystemClient.util;

import java.io.*;
import java.util.Properties;

/**
 * 赖小燚
 * www.louis.com
 */
public class PropertiesUtil {
    private static Properties properties = new Properties();

    //properties文件名
    private static final String PROPERTIES_FILE_NAME = "component-config/TeaBudIdentify.properties";

    /**
     * 初始化properties
     */
    private static void initProperties(){
        try{
            String path = ClassLoader.getSystemResource(PROPERTIES_FILE_NAME).getPath();
            FileInputStream fis = new FileInputStream(path);
            properties.load(fis);
            fis.close();
        }catch (Exception ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * 获取指定key的value
     * @param key
     */
    public static String getValue(String key){
        if(properties.isEmpty()){   //如果properties为空，则初始化一次
            initProperties();
        }
        return properties.getProperty(key);
    }

    public static void setValue(String key,String value){
        if(properties.isEmpty()){
            //如果properties为空，则初始化
            initProperties();
        }
        properties.setProperty(key,value);
        //保存文件
        String path = ClassLoader.getSystemResource(PROPERTIES_FILE_NAME).getPath();
        try {
            System.out.println(path);
            FileOutputStream fos = new FileOutputStream(path);
            System.out.println(properties);
            System.out.println(1);
            properties.store(fos,"");
            System.out.println(2);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        initProperties();
        setValue("lastJFileChooserDirectory","123456");
    }

}
