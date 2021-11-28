package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.util.ScreenUtils;

import javax.swing.*;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaBudIdentifyPanel extends JPanel {

    public void init(){
        JSplitPane jSplitPane = new JSplitPane();
        this.add(jSplitPane);
        jSplitPane.setContinuousLayout(true);
        jSplitPane.setDividerLocation(ScreenUtils.getScreenWidth()/10);
        jSplitPane.setDividerSize(7);
    }

}
