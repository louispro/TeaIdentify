package com.louis.teaSystemClient.util;

import javax.tools.Tool;
import java.awt.*;

/**
 * 赖小燚
 * www.louis.com
 */
public class ScreenUtils {

    final static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public static int getScreenWidth(){
//        return device.getDefaultConfiguration().getBounds().width;
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public static int getScreenHeight(){
//        return device.getDefaultConfiguration().getBounds().height;
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    //获取任务栏高度
    public static int getInset(){
//        return device.
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(device.getDefaultConfiguration());
        return screenInsets.bottom;
    }

    public static void main(String[] args) {
        System.out.println(ScreenUtils.getScreenWidth()+"*"+ScreenUtils.getScreenHeight());
        System.out.println(getInset());
    }
}
