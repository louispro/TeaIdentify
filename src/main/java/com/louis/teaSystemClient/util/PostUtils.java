package com.louis.teaSystemClient.util;

import com.louis.teaSystemClient.listener.FailListener;
import com.louis.teaSystemClient.listener.SuccessListener;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * 赖小燚
 * www.louis.com
 */
public class PostUtils {
    public static void post(String url, SuccessListener sListener, FailListener fListener) {
        postWithParams(url, new HashMap<>(),sListener,fListener);
    }

    public static void postWithParams(String url, Map<String, String> params, SuccessListener sListener, FailListener fListener) {
        List<NameValuePair> pairs = generatePairs(params);
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(CookiesHolder.getCookieStore()).build();
        CloseableHttpResponse response = null;
        try {

            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(3000) //��������Ӧ��ʱʱ��
                    .setConnectTimeout(3000) //���ӷ�������ʱʱ��
                    .build();

            httpPost.setConfig(requestConfig);

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            // �ɿͻ���ִ��(����)����
            response = httpClient.execute(httpPost);


            // ����Ӧģ���л�ȡ��Ӧʵ��
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                sListener.success(EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            fListener.fail();
        } finally {
            try {
                // �ͷ���Դ
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<NameValuePair> generatePairs(Map<String, String> params) {
        if (params == null || params.size() == 0) {
            return Collections.emptyList();
        }

        List<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String,String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key == null) {
                continue;
            }

            pairs.add(new BasicNameValuePair(key, value));
        }

        return pairs;
    }
}
