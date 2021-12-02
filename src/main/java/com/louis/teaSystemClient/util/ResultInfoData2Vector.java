package com.louis.teaSystemClient.util;

import com.louis.teaSystemClient.pojo.ResultInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * 赖小燚
 * www.louis.com
 */
public class ResultInfoData2Vector {
    public static Vector<Vector> convertResultInfoData2Vector(ResultInfo info){
        List list = (List) info.getData();
        Vector<Vector> vectors = new Vector<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap map = (HashMap) list.get(i);
            Vector vector = new Vector();
            vector.addAll(map.values());
            vectors.add(vector);
        }
        return vectors;
    }

    public static void main(String[] args) {
        ResultInfo resultInfo = JsonUtils.parseResult("{\"flag\":true,\"data\":[{\"id\":1,\"modelName\":\"Fast-RCNN\"},{\"id\":2,\"modelName\":\"YOLO\"}],\"message\":\"请求成功\"}");
        Vector<Vector> vectors = convertResultInfoData2Vector(resultInfo);
        for(Vector vector:vectors){
            System.out.println(vector);
            System.out.println(vector.get(1));
        }
    }
}
