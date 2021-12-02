package com.louis.teaSystemService.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 赖小燚
 * www.louis.com
 */
public class DownloadUtil {

    public static ResponseEntity<byte[]> downloadImage(HttpServletRequest request,String filename) throws IOException {
        ServletContext context = request.getServletContext();
        System.out.println("下载的文件名："+filename);
//        String filename = "girl.jpg";
        //1.获取要下载文件的真实路径
//        String realPath = context.getRealPath("static/images/teaIdentify/identify/identifyTeaBud/girl.jpg");
        String realPath = context.getRealPath("/WEB-INF/classes/static/images/teaIdentify/service/originTeaBud/"+filename);
        System.out.println("文件完整路径:"+realPath);
        //2.获取下载文件的流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        System.out.println(1);
        byte[] tmp = new byte[fileInputStream.available()];
        System.out.println(2);
        fileInputStream.read(tmp);
        System.out.println(3);
        fileInputStream.close();    //关闭流
        System.out.println(4);
        //3.返回流
        HttpHeaders headers = new HttpHeaders();
        System.out.println(5);
        headers.set("Content-Disposition","attachment;filename="+filename);
        System.out.println(6);
        return new ResponseEntity<>(tmp,headers, HttpStatus.OK);
    }
}
