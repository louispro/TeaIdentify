package com.louis.study.awt;

import jdk.dynalink.beans.StaticClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

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




    public void init(){

    }

    public static void main(String[] args) {


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
            if(selectX >= 0 && selectY <= 0){
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
