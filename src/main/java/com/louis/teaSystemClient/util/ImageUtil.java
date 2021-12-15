package com.louis.teaSystemClient.util;

import com.louis.teaSystemClient.Component.TeaBudProcessPanel;

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

//    public static Image scaleImageSize(ImageIcon imageIcon,BigDecimal rate){
//        int imageWidth = imageIcon.getIconWidth();
//        int imageHeight = imageIcon.getIconHeight();
//        //获取本机屏幕宽高
//        int deviceWidth = ScreenUtils.getScreenWidth();
//        int labelWidth = (deviceWidth-deviceWidth/10)-200;
////        int labelWidth = 600;
//        //当图片宽度大于屏幕高度时,需要进行缩放
//        if(imageWidth>=labelWidth){
//            //计算缩放比例
//            rate = new BigDecimal(Integer.toString(labelWidth)).divide(new BigDecimal(Integer.toString(imageWidth)),2,RoundingMode.FLOOR).subtract(new BigDecimal("0.02"));
//            TeaBudProcessPanel.setRate(rate);
//            System.out.println(rate);
//            int scaleWidth = (int)((imageWidth)*rate.doubleValue());
//            int scaleHeight = (int)((imageHeight)*rate.doubleValue());
//            return imageIcon.getImage().getScaledInstance(scaleWidth,scaleHeight, Image.SCALE_DEFAULT);
//        }
//        return imageIcon.getImage().getScaledInstance(imageWidth,imageHeight,Image.SCALE_DEFAULT);
//    }

    /**
     * 放大图片
     * @param imageIcon
     * @param rate
     */
//    public static Image biggerImage(ImageIcon imageIcon,BigDecimal rate){
//        rate = rate.add(new BigDecimal("0.1"));
//        System.out.println("原始宽度："+imageIcon.getIconWidth());
//        System.out.println("原始高度："+imageIcon.getIconHeight());
//        int width = (int)(imageIcon.getIconWidth()*rate.doubleValue());
//        int height = (int)(imageIcon.getIconHeight()*rate.doubleValue());
//        System.out.println("rate:"+rate);
//        System.out.println("width:"+width);
//        System.out.println("height:"+height);
//        TeaBudProcessPanel.setRate(rate);
//        return imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
//    }

    /**
     * 缩小图片
     * @param imageIcon
     * @param rate
     * @return
     */
//    public static Image smallerImage(ImageIcon imageIcon,BigDecimal rate){
//        if(rate.doubleValue() > 0.2)
//            rate = rate.subtract(new BigDecimal("0.1"));
//        int width = (int)(imageIcon.getIconWidth()*rate.doubleValue());
//        int height = (int)(imageIcon.getIconHeight()*rate.doubleValue());
//        System.out.println("width:"+width);
//        System.out.println("height:"+height);
//        TeaBudProcessPanel.setRate(rate);
//        return imageIcon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
//    }



    public static void main(String[] args) {
        scaleImageSize("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\originTeaBud\\000009.JPG");
    }
}
