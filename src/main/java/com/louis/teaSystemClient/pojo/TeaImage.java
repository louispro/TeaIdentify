package com.louis.teaSystemClient.pojo;

import com.louis.teaSystemClient.util.ScreenUtils;
import com.mchange.v2.c3p0.codegen.JdbcProxyGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaImage {
    //图片路径，默认为D:\Codes\JavaCode\TeaSystem\src\main\resources\static\images\teaIdentify\client\originTeaBud\000002.JPG
    private String path = "D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\originTeaBud\\000002.JPG";
    //图片对象
    private ImageIcon imageIcon = new ImageIcon(path);
    //图片宽度
    private int WIDTH = imageIcon.getIconWidth();
    //图片高度
    private int HEIGHT = imageIcon.getIconHeight();
    //图片名，默认未000002.JPG
    private String imageName = "000002.JPG";
    //图片格式
    private String format = "JPG";
    //图片信息
    private String info;
    //图片缩放比例
    private BigDecimal rate = new BigDecimal("1");

    public TeaImage() {
        //缩放图片适应图片大小
        suitScreen();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        System.out.println("rate:"+rate);
        if(rate.doubleValue() <= 0.1){
            //当缩放比例小于0.1的时候便不再缩放
            this.rate = new BigDecimal("0.1");
            return;
        }
        if(rate.doubleValue() >= 2.0){
            //当缩放比例大于2.0的时候不再放大
            this.rate = new BigDecimal("2.0");
            return;
        }
        this.rate = rate;
    }

    public String getInfo() {
        long bits = new File(path).length();
        info = " "+imageName+" "+WIDTH+"*"+HEIGHT+" "+format+" ";
        double size;
        if(bits >= 1024*1024){
            size = new BigDecimal(Long.toString(bits)).divide(new BigDecimal(Integer.toString(1024*1024)),1, RoundingMode.FLOOR).doubleValue();
            info = info+size+"M";
        } else {
            size = new BigDecimal(Long.toString(bits)).divide(new BigDecimal(Integer.toString(1024)),1, RoundingMode.FLOOR).doubleValue();
            info = info+size+"KB";
        }
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setImageIcon(){
        int width = (int)(WIDTH*rate.doubleValue());
        int height = (int)(HEIGHT*rate.doubleValue());
        Image image = imageIcon.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT);
        imageIcon.setImage(image);
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 使图片适应屏幕大小
     */
    private void suitScreen(){
        //获取本机屏幕宽高
        int deviceWidth = ScreenUtils.getScreenWidth();
        int labelWidth = (deviceWidth-deviceWidth/10);
        //当图片宽度大于屏幕高度时,需要进行缩放
        if(WIDTH >= labelWidth){
            //计算缩放比例
            rate = new BigDecimal(Integer.toString(labelWidth)).divide(new BigDecimal(Integer.toString(WIDTH)),2,RoundingMode.FLOOR).subtract(new BigDecimal("0.02"));
            int scaleWidth = (int)(WIDTH*rate.doubleValue());
            int scaleHeight = (int)(HEIGHT*rate.doubleValue());
            Image image = imageIcon.getImage().getScaledInstance(scaleWidth,scaleHeight, Image.SCALE_DEFAULT);
            imageIcon.setImage(image);
        }
    }

    /**
     * 当图片路径改变的时候，需要重设图片的一些属性
     */
    public void setAll(String path){
        this.path = path;
        this.imageIcon = new ImageIcon(path);
        this.WIDTH = this.imageIcon.getIconWidth();
        this.HEIGHT = this.imageIcon.getIconHeight();
        this.imageName = new File(path).getName();
        this.format = this.imageName.split("\\.")[1].toUpperCase();
        this.info = this.getInfo();
        suitScreen();
    }

    public static void main(String[] args) {
        System.out.println(new File("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\originTeaBud\\000009.JPG").getName());
    }




}
