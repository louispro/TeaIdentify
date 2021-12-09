package com.louis.teaSystemClient.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 赖小燚
 * www.louis.com
 */
public class NetUtil{

    /**
     * 检测网络是否正常
     * @return
     */
    public static boolean checkWeb(){
        //可关闭的httpclient客户端，相当于打开的一个浏览器
        CloseableHttpClient client = HttpClients.createDefault();
        //构造httpPost请求对象
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if(status == 200){
                System.out.println("网络连接正常");
                return true;    //网络连接正常
            }
        } catch (IOException e) {
        }
        return false;
    }

    /**
     * 检测服务器是否正常
     * @return
     */
    public static boolean checkService(){
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1",8080));
            if(socket.isConnected()){
                System.out.println("服务器开启");
                return true;
            }
            return socket.isConnected();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
        return false;
    }

    public static void main(String[] args) {
        checkWeb();
        checkService();
    }
}
