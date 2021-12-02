package com.louis.teaSystemService.pojo;

/**
 * 赖小燚
 * www.louis.com
 */
public class IdentifyModel {
    private Integer id;
    private String modelName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                '}';
    }
}
