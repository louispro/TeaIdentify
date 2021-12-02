package com.louis.teaSystemService.controller;

import com.louis.teaSystemService.pojo.ResultInfo;
import com.louis.teaSystemService.service.IdentifyModelService;
import com.louis.teaSystemService.util.DownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.louis.teaSystemService.pojo.IdentifyModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

/**
 * 赖小燚
 * www.louis.com
 */
@Controller
@RequestMapping("/model")
public class IdentifyModelController {
    @Autowired
    private IdentifyModelService identifyModelService;

    //获取所有模型
    @GetMapping("getModels")
    @ResponseBody
    public ResultInfo getModelAll(){
        List<IdentifyModel> identifyModelList = identifyModelService.getModelAll();
        return new ResultInfo(true,identifyModelList,"请求成功");
    }


    //获取嫩芽图像
    @PostMapping ("originTeaBud")
    public ResponseEntity<byte[]> getOriginTeaBud(@RequestParam("fileName") MultipartFile file, Model model, HttpServletRequest request) throws IOException {
        System.out.println("上传的文件信息");
        String filename = file.getOriginalFilename();
        System.out.println("文件名："+file.getName());
        System.out.println("文件名字："+file.getOriginalFilename());
        //保存文件
        try {
            file.transferTo(new File("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\service\\originTeaBud\\"+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回识别之后的图片
        return DownloadUtil.downloadImage(request,filename);
    }


}
