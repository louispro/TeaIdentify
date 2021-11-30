package com.louis.teaSystemService.controller;

import com.louis.teaSystemService.pojo.Model;
import com.louis.teaSystemService.pojo.ResultInfo;
import com.louis.teaSystemService.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 赖小燚
 * www.louis.com
 */
@Controller
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    //获取所有模型
    @GetMapping("getModels")
    @ResponseBody
    public ResultInfo getModelAll(){
        List<Model> modelList = modelService.getModelAll();
        return new ResultInfo(true,modelList,"请求成功");
    }
}
