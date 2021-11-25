package com.louis.teaSystemClient.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.louis.teaSystemClient.pojo.ResultInfo;

import java.io.IOException;

/**
 * ��С�D
 * www.louis.com
 */
public class JsonUtils {

    public static ResultInfo parseResult(String json){

        ObjectMapper om = new ObjectMapper();
        ResultInfo resultInfo = null;
        try {
            resultInfo = om.readValue(json, ResultInfo.class);
            System.out.println("JsonUtils:"+resultInfo);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultInfo;
    }

    public static void main(String[] args) {
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser jsonParser = jsonFactory.createParser("{\"flag\":true,\"data\":1,\"message\":\"��¼�ɹ�\"}");
            ResultInfo resultInfo = (ResultInfo)jsonParser.currentValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
