package com.louis.teaSystemService.util;

/**
 * 赖小燚
 * www.louis.com
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 执行python脚本的工具
 */
public class PythonUtil {

    public static void execPythonWithParam(String param){
        System.out.println("开始执行python脚本");
        String[] args1 = {"python","D:\\Codes\\PythonCode\\TeaIdentify\\venv\\include\\faster-rcnn-pytorch-master\\predict.py",param};
//        String[] args1 = {"python","D:\\Codes\\PythonCode\\TeaIdentify\\venv\\include\\test.py","D:\\Codes\\PythonCode\\TeaIdentify\\venv\\include\\faster-rcnn-pytorch-master\\img\\000002.JPG"};
        Process pr = null;
        BufferedReader in = null;
        try {
            pr = Runtime.getRuntime().exec(args1);
            in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while((line=in.readLine())!=null){
                System.out.println(line);
            }
            pr.waitFor();
            System.out.println("python脚本执行结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
