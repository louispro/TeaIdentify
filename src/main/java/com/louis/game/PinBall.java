package com.louis.game;

/**
 * @��С�D
 * @www.louis_lai.com
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

/**
 * ����С��Ϸ
 */
public class PinBall {

    Frame frame = new Frame("����С��Ϸ");

    //������
    private final int TABLE_WIDTH = 600;
    private final int TABLE_HEIGHT = 800;

    //���ĵĿ��
    private final int RACKET_WIDTH = 120;
    private final int RACKET_HEIGHT = 25;

    //С��Ĵ�С
    private final int BALL_SIZE = 32;

    //С������
    int ballX = 284;
    int ballY = 0;

    //С����x,y�����������ƶ����ٶ�
    private int speedY = 20;
    private int speedX = 10;

    //��¼��������
    private int racketX = 240;
    private final int racketY = 650;

    //��Ϸ�Ƿ����
    private boolean isOver = false;
    Timer timer;

    //�Զ��廭���ڲ���
    private class GameCanvas extends Canvas{
        @Override
        public void paint(Graphics g) {
            //��Ϸ��ʼ
            if(!isOver){
                //����С��
                g.setColor(Color.ORANGE);
                g.fillOval(ballX,ballY,BALL_SIZE,BALL_SIZE);
                //��������
                g.setColor(Color.BLACK);
                g.fillRect(racketX,racketY,RACKET_WIDTH,RACKET_HEIGHT);
            }else{
                //��Ϸ����
                g.setColor(Color.RED);
                g.setFont(new Font("Times",Font.BOLD,30));
                g.drawString("��Ϸ������",50,200);
            }
        }
    }

    //����
    GameCanvas gameCanvas = new GameCanvas();

    //������Ϸ����ԭ��X����
    public int calcuteX(){
        //��ȡ�豸�߶�
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        GraphicsConfiguration[] gc = gd[1].getConfigurations();
        int width = (int)gc[0].getBounds().getWidth();
        return width/2-TABLE_WIDTH/2;
    }
    //������Ϸ����Yԭ������
    public int calcuteY(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        GraphicsConfiguration[] gc = gd[1].getConfigurations();
        int height = (int)gc[0].getBounds().getHeight();
        return height/2-TABLE_HEIGHT/2;
    }

    public void init(){
        //��������任
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                //�����ƶ�
                if(keyCode == KeyEvent.VK_LEFT){
                    if(racketX>0){
                        racketX -= 20;
                    }
                }
                //�����ƶ�
                if(keyCode == KeyEvent.VK_RIGHT){
                    if(racketX<(TABLE_WIDTH-RACKET_WIDTH)){
                        racketX += 20;
                    }
                }
                gameCanvas.repaint();
            }
        };

        //С���ƶ�
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //�������ұ߽�
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

        //�ر���Ϸ
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
        //��ȡ����ͼ�λ�������
        PinBall pinBall = new PinBall();
        pinBall.init();
        double x = pinBall.calcuteX();
        double y = pinBall.calcuteY();
        System.out.println(x+","+y);
    }
}
