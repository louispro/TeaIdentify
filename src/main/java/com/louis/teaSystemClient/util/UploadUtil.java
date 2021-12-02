package com.louis.teaSystemClient.util;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 赖小燚
 * www.louis.com
 */
//上传文件
public class UploadUtil {

    //上传图片
    public static void uploadImage(String url){
        //可关闭的httpclient客户端，相当于打开的一个浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        //构造httpPost请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置请求头
//        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
//        httpPost.addHeader("Referer","http://localhost:8080/model/identify");
        //构造文件上传使用的entity
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Consts.UTF_8);       //设置编码
        builder.setContentType(ContentType.MULTIPART_FORM_DATA);    //设置为表单form enctype为application/m
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);  //设置浏览器模式
        HttpEntity httpEntity = builder.addBinaryBody("fileName",new File("src/main/resources/static/images/teaIdentify/girl.jpg"))
                .build();
        httpPost.setEntity(httpEntity);
        //响应
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            System.out.println("响应的状态码为："+status);
            if(status == HttpStatus.SC_OK){
                //获取响应结果
                HttpEntity entity = response.getEntity();
                //对HttpEntity操作的工具类
                String result = EntityUtils.toString(entity, StandardCharsets.UTF_8);   //获取响应体的字符串形式
                //确保流关闭
                EntityUtils.consume(entity);
            }else {
                System.out.println("请求异常");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        uploadImage("http://localhost:8080/model/originTeaBud");
    }
}