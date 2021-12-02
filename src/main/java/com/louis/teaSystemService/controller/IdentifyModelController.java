package com.louis.teaSystemService.controller;

import com.louis.teaSystemService.pojo.ResultInfo;
import com.louis.teaSystemService.service.IdentifyModelService;
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
    public String getOriginTeaBud(@RequestParam("fileName") MultipartFile file, Model model, HttpServletRequest request){
        System.out.println("上传的文件信息");
        System.out.println("文件名："+file.getName());
        System.out.println("文件名字："+file.getOriginalFilename());
        //保存文件
        try {
            file.transferTo(new File("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\identify\\identifyTeaBud\\"+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<String> headNames = request.getHeaderNames();
        while (headNames.hasMoreElements()){
            String headName = headNames.nextElement();
            System.out.println(headName+"===>"+request.getHeader(headName));
        }
        model.addAttribute("imageName",file.getOriginalFilename());


        return "success";
    }

    //嫩芽识别
    @RequestMapping("/identify")
    public ResponseEntity<byte[]> identifyTeaBud(HttpServletRequest request) throws IOException {
        ServletContext context = request.getServletContext();
        //1.获取要下载文件的真实路径
        String realPath = context.getRealPath("/static/images/teaIdentify/identify/identifyTeaBud/girl.jpg");
        System.out.println("文件完整路径:"+realPath);
        //2.获取下载文件的流
        FileInputStream fileInputStream = new FileInputStream(realPath);
        byte[] tmp = new byte[fileInputStream.available()];
        fileInputStream.read(tmp);
        fileInputStream.close();    //关闭流
        //3.返回流
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition","attachment;filename="+"jquery-1.9.1.min.js");
        return new ResponseEntity<>(tmp,headers, HttpStatus.OK);
    }
}
