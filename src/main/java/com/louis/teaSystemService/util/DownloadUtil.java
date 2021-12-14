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
//        String realPath = context.getRealPath("/WEB-INF/classes/static/images/teaIdentify/service/originTeaBud/"+filename);
        String realPath = "D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\service\\identifyTeaBud\\"+filename;
        System.out.println("文件完整路径:"+realPath);
        //2.获取下载文件的流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        byte[] tmp = new byte[fileInputStream.available()];
        fileInputStream.read(tmp);
        fileInputStream.close();    //关闭流
        //3.返回流
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition","attachment;filename="+filename);
        return new ResponseEntity<>(tmp,headers, HttpStatus.OK);
    }
}
