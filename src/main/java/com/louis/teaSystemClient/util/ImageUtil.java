package com.louis.teaSystemClient.util;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 赖小燚
 * www.louis.com
 */
public class ImageUtil {

    public static Image scaleImageSize(String imagePath){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        int imageWidth = imageIcon.getIconWidth();
        int imageHeight = imageIcon.getIconHeight();
        //获取本机屏幕宽高
        int deviceWidth = ScreenUtils.getScreenWidth();
        int labelWidth = (deviceWidth-deviceWidth/10)/2;
        //当图片宽度大于屏幕高度时,需要进行缩放
        if(imageWidth>=labelWidth){
            //计算缩放比例
            System.out.println(new BigDecimal(Integer.toString(labelWidth)).divide(new BigDecimal(Integer.toString(imageWidth)),2, RoundingMode.FLOOR));
            double rate = new BigDecimal(Integer.toString(labelWidth)).divide(new BigDecimal(Integer.toString(imageWidth)),2,RoundingMode.FLOOR).subtract(new BigDecimal("0.005")).doubleValue();
            int scaleWidth = (int)((imageWidth)*rate);
            int scaleHeight = (int)((imageHeight)*rate);
            return imageIcon.getImage().getScaledInstance(scaleWidth,scaleHeight, Image.SCALE_DEFAULT);
        }
        return imageIcon.getImage().getScaledInstance(imageWidth,imageHeight,Image.SCALE_DEFAULT);
    }

    public static void main(String[] args) {
        scaleImageSize("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\originTeaBud\\000009.JPG");
    }
}
