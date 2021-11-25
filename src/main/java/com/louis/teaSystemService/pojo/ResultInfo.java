package com.louis.teaSystemService.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ÀµÐ¡ D
 * www.louis.com
 */
@JsonIgnoreProperties(value = {"handler"})
public class ResultInfo {

    private boolean flag;
    private Object data;
    private String message;

    public ResultInfo(boolean flag, Object data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "flag=" + flag +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

}
