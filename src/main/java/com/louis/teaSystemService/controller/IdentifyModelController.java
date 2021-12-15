package com.louis.teaSystemService.controller;

import com.louis.teaSystemService.pojo.ResultInfo;
import com.louis.teaSystemService.service.IdentifyModelService;
import com.louis.teaSystemService.util.DownloadUtil;
import com.louis.teaSystemService.util.FileUtil;
import com.louis.teaSystemService.util.PythonUtil;
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
    @PostMapping ("identify")
    public ResponseEntity<byte[]> getOriginTeaBud(@RequestParam("fileName") MultipartFile file, Model model, HttpServletRequest request) throws IOException {
        System.out.println("上传的文件信息");
        //上传文件的原始名字
        String originalFilename = file.getOriginalFilename();
        System.out.println("文件名字："+file.getOriginalFilename());
        String newFilename = null;
        //保存文件
        try {
            //重新构造文件名
            String filename = originalFilename.split("\\.")[0];
            String format = originalFilename.split("\\.")[1];
            System.out.println("文件名："+filename);
            System.out.println("文件格式："+format);
            System.out.println(originalFilename.split("\\.").length);
            newFilename = FileUtil.createFileName(filename,format);
            System.out.println("开始保存文件："+newFilename);
            //将文件保存在java的service/originTeaBud目录下
            file.transferTo(new File("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\service\\originTeaBud\\"+newFilename));
            //将文件保存在python的faster-rcnn-pytorch-master/img目录下
//            file.transferTo(new File("D:\\Codes\\PythonCode\\TeaIdentify\\venv\\include\\faster-rcnn-pytorch-master\\img\\"+newFilename));
            FileUtil.copyImage("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\service\\originTeaBud\\"+newFilename,
                    "D:\\Codes\\PythonCode\\TeaIdentify\\venv\\include\\faster-rcnn-pytorch-master\\img\\"+newFilename);
            System.out.println("成功保存");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //识别图片
        PythonUtil.execPythonWithParam("D:\\Codes\\PythonCode\\TeaIdentify\\venv\\include\\faster-rcnn-pytorch-master\\img\\"+newFilename);
        //返回识别之后的图片
        return DownloadUtil.downloadImage(request,newFilename);
    }
}
