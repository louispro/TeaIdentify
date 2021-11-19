package com.louis.game;

/**
 * @赖小燚
 * @www.louis_lai.com
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

/**
 * 弹球小游戏
 */
public class PinBall {

    Frame frame = new Frame("弹球小游戏");

    //桌面宽高
    private final int TABLE_WIDTH = 600;
    private final int TABLE_HEIGHT = 800;

    //球拍的宽高
    private final int RACKET_WIDTH = 120;
    private final int RACKET_HEIGHT = 25;

    //小球的大小
    private final int BALL_SIZE = 32;

    //小球坐标
    int ballX = 284;
    int ballY = 0;

    //小球在x,y两个方向上移动的速度
    private int speedY = 20;
    private int speedX = 10;

    //记录球拍坐标
    private int racketX = 240;
    private final int racketY = 650;

    //游戏是否结束
    private boolean isOver = false;
    Timer timer;

    //自定义画布内部类
    private class GameCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            //游戏开始
            if(!isOver){
                //绘制小球
                g.setColor(Color.ORANGE);
                g.fillOval(ballX,ballY,BALL_SIZE,BALL_SIZE);
                //绘制球拍
                g.setColor(Color.BLACK);
                g.fillRect(racketX,racketY,RACKET_WIDTH,RACKET_HEIGHT);
            }else{
                //游戏结束
                g.setColor(Color.RED);
                g.setFont(new Font("Times",Font.BOLD,30));
                g.drawString("游戏结束！",50,200);
            }
        }
    }

    //画布
    GameCanvas gameCanvas = new GameCanvas();

    //计算游戏界面原点X坐标
    public int calcuteX(){
        //获取设备高度
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        GraphicsConfiguration[] gc = gd[1].getConfigurations();
        int width = (int)gc[0].getBounds().getWidth();
        return width/2-TABLE_WIDTH/2;
    }
    //计算游戏界面Y原点坐标
    public int calcuteY(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        GraphicsConfiguration[] gc = gd[1].getConfigurations();
        int height = (int)gc[0].getBounds().getHeight();
        return height/2-TABLE_HEIGHT/2;
    }

    public void init(){
        //球拍坐标变换
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                //向左移动
                if(keyCode == KeyEvent.VK_LEFT){
                    if(racketX>0){
                        racketX -= 20;
                    }
                }
                //向右移动
                if(keyCode == KeyEvent.VK_RIGHT){
                    if(racketX<(TABLE_WIDTH-RACKET_WIDTH)){
                        racketX += 20;
                    }
                }
                gameCanvas.repaint();
            }
        };

        //小球移动
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //碰到左右边界
                if(ballX<=0 || ballX>=(TABLE_WIDTH-BALL_SIZE)){
                    speedX = -speedX;
                }
                if(ballY<0 || (ballY>racketY-BALL_SIZE && ballX>racketX && ballX < racketX+RACKET_WIDTH)){
                    speedY = -speedY;
                }
                if(ballY > racketY-BALL_SIZE && (ballX < racketX || ballX > racketX+RACKET_WIDTH)){
                    timer.stop();
                    isOver = true;
                    gameCanvas.repaint();
                }
                System.out.println("X:"+speedX);
                System.out.println("Y:"+speedY);
                ballX+=speedX;
                ballY+=speedY;
                gameCanvas.repaint();
            }
        };
        timer = new Timer(100,task);
        timer.start();


        frame.addKeyListener(keyListener);
        gameCanvas.addKeyListener(keyListener);

        //关闭游戏
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        gameCanvas.setBounds(0,0,TABLE_WIDTH,TABLE_HEIGHT);

        frame.add(gameCanvas);
        frame.setBounds(calcuteX(),calcuteY(),TABLE_WIDTH,TABLE_HEIGHT);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //获取本地图形环境对象
        PinBall pinBall = new PinBall();
        pinBall.init();
        double x = pinBall.calcuteX();
        double y = pinBall.calcuteY();
        System.out.println(x+","+y);
    }
}
