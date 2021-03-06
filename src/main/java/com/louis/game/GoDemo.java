package com.louis.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class GoDemo {

    //下面三个位图分别代表棋盘、黑子、白子
    BufferedImage table;
    BufferedImage black;
    BufferedImage white;

    //当鼠标移动时的选择框
    BufferedImage selected;

    //定义棋盘的大小
    private static int BOARD_SIZE = 15;
    //定义棋盘宽高
    private final int TABLE_WIDTH = 535;
    private final int TABLE_HEIGHT = 536;
    //定义棋盘坐标的像素值和棋盘数字之间的比率
    private final int RATE = TABLE_WIDTH/BOARD_SIZE;

    //定义棋盘坐标的像素值和棋盘数组之间的偏移距离
    private final int X_OFFSET = 5;
    private final int Y_OFFSET = 6;

    //定义一个二维数组来充当棋盘
    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

    //五子棋游戏的窗口
    JFrame f = new JFrame("五子棋");

    //五子棋游戏对应的Canvas组件
    ChessBoard chessBoard = new ChessBoard();

    //当前选中点的坐标
    private int selectX = -1;
    private int selectY = -1;

    //用户和电脑是否下棋
    private boolean isUserGo = false;
    private boolean isRobotGo = false;

    //定时器，用户下完一秒之后电脑下棋
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //用户下完之后才能下
            if(isUserGo){
                //电脑下棋
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                Random random = new Random();
                int robotX = random.nextInt(15);
                int robotY = random.nextInt(15);
                while(!"null".equals(board[robotX][robotY])){
                    Random random1 = new Random();
                    robotX = random1.nextInt(15);
                    robotY = random1.nextInt(15);
                }
                board[robotX][robotY] = "white";
                chessBoard.repaint();
                isUserGo = false;
            }
        }
    });


    public void init() throws Exception{
        table = ImageIO.read(new File("src/main/resources/images/go/board.jpg"));
        black = ImageIO.read(new File("src/main/resources/images/go/black.gif"));
        white = ImageIO.read(new File("src/main/resources/images/go/white.gif"));
        selected = ImageIO.read(new File("src/main/resources/images/go/selected.gif"));

        for(int i = 0;i < BOARD_SIZE;i++){
            for(int j = 0;j < BOARD_SIZE;j++){
                board[i][j] = "null";
            }
        }

        chessBoard.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));
        chessBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //用户下棋
                while(isUserGo==false){
                    int xPos = (int)((e.getX()-X_OFFSET)/RATE);
                    int yPos = (int)((e.getY()-Y_OFFSET)/RATE);
                    if("null".equals(board[xPos][yPos])){
                        board[xPos][yPos] = "black";
                        isUserGo = true;
                    }
                }
                chessBoard.repaint();
            }

            //鼠标退出棋盘后，复位选中点坐标
            @Override
            public void mouseExited(MouseEvent e) {
                selectX = -1;
                selectY = -1;
                chessBoard.repaint();
            }
        });

        chessBoard.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                selectX = (e.getX()-X_OFFSET)/RATE;
                selectY = (e.getY()-Y_OFFSET)/RATE;
                chessBoard.repaint();
            }
        });

        timer.start();
        f.add(chessBoard);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {
    new GoDemo().init();

//        System.out.println("能读的图形文件格式");
//        for(String canRead : ImageIO.getReaderFormatNames()){
//            System.out.print(canRead+"   ");
//        }
//        System.out.println();
//        System.out.println("能写的图形文件格式");
//        for(String canWrite : ImageIO.getWriterFormatNames()){
//            System.out.print(canWrite+"   ");
//        }
    }

    //五子棋棋盘
    class ChessBoard extends JPanel{
        @Override
        public void paint(Graphics g) {
            //绘制五子棋盘
            g.drawImage(table,0 ,0,null);
            //绘制选中点的红框
            if(selectX >= 0 && selectY >= 0){
                g.drawImage(selected,selectX*RATE+X_OFFSET,selectY*RATE+Y_OFFSET,null);
            }
            //遍历数组绘制棋子
            for(int i = 0; i < BOARD_SIZE;i++){
                for(int j = 0;j < BOARD_SIZE;j++){
                    //绘制黑棋
                    if(board[i][j].equals("black")){
                        g.drawImage(black,i*RATE+X_OFFSET,j*RATE+Y_OFFSET,null);
                    }
                    //绘制白棋
                    if(board[i][j].equals("white")){
                        g.drawImage(white,i*RATE+X_OFFSET,j*RATE+Y_OFFSET,null);
                    }
                }
            }
        }
    }
}
