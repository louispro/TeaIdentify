package com.louis.TeaIdentify.util;

import java.awt.*;

/**
 * @ÀµÐ¡ D
 * @www.louis_lai.com
 */

public class ScreenUtil {

    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public static void main(String[] args) {
        ScreenUtil.getScreenHeight();
        ScreenUtil.getScreenWidth();
    }
}
