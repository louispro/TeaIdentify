package com.louis.study.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JProgressBarDemo2 {

    JFrame jf = new JFrame("测试进度条");
    JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL);

    public void init(){
        final SimulateActivity simulateActivity = new SimulateActivity(100);
        new Thread(simulateActivity).start();
        bar.setStringPainted(true);
        bar.setBorderPainted(true);
        bar.setMinimum(0);
        bar.setMaximum(simulateActivity.getAmount());
        new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bar.setValue(simulateActivity.getCurrent());
            }
        }).start();
        bar.setPreferredSize(new Dimension(100,20));
        jf.setLayout(new FlowLayout());
        jf.add(bar);
        jf.setPreferredSize(new Dimension(400,300));
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new JProgressBarDemo2().init();
    }
}

class SimulateActivity implements Runnable{
    //任务的当前完成量
    private volatile  int current;
    //总任务量
    private int amount;

    public SimulateActivity(int amount){
        this.current = 0;
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }

    public int getCurrent(){
        return this.current;
    }
    @Override
    public void run() {
        while(current < amount){
            try{
                Thread.sleep(50);
            }catch (Exception e){
                e.printStackTrace();
            }
            this.current++;
        }
    }
}
