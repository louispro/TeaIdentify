package com.louis.study.awt;

import jdk.dynalink.beans.StaticClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @��С�D
 * @www.louis_lai.com
 */
public class GoDemo {

    //��������λͼ�ֱ�������̡����ӡ�����
    BufferedImage table;
    BufferedImage black;
    BufferedImage white;

    //������ƶ�ʱ��ѡ���
    BufferedImage selected;

    //�������̵Ĵ�С
    private static int BOARD_SIZE = 15;
    //�������̿��
    private final int TABLE_WIDTH = 535;
    private final int TABLE_HEIGHT = 536;
    //�����������������ֵ����������֮��ı���
    private final int RATE = TABLE_WIDTH/BOARD_SIZE;

    //�����������������ֵ����������֮���ƫ�ƾ���
    private final int X_OFFSET = 5;
    private final int Y_OFFSET = 6;

    //����һ����ά�������䵱����
    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

    //��������Ϸ�Ĵ���
    JFrame f = new JFrame("������");

    //��������Ϸ��Ӧ��Canvas���
    ChessBoard chessBoard = new ChessBoard();

    //��ǰѡ�е������
    private int selectX = -1;
    private int selectY = -1;




    public void init(){

    }

    public static void main(String[] args) {


//        System.out.println("�ܶ���ͼ���ļ���ʽ");
//        for(String canRead : ImageIO.getReaderFormatNames()){
//            System.out.print(canRead+"   ");
//        }
//        System.out.println();
//        System.out.println("��д��ͼ���ļ���ʽ");
//        for(String canWrite : ImageIO.getWriterFormatNames()){
//            System.out.print(canWrite+"   ");
//        }
    }

    //����������
    class ChessBoard extends JPanel{
        @Override
        public void paint(Graphics g) {
            //������������
            g.drawImage(table,0 ,0,null);
            //����ѡ�е�ĺ��
            if(selectX >= 0 && selectY <= 0){
                g.drawImage(selected,selectX*RATE+X_OFFSET,selectY*RATE+Y_OFFSET,null);
            }
            //���������������
            for(int i = 0; i < BOARD_SIZE;i++){
                for(int j = 0;j < BOARD_SIZE;j++){
                    //���ƺ���
                    if(board[i][j].equals("black")){
                        g.drawImage(black,i*RATE+X_OFFSET,j*RATE+Y_OFFSET,null);
                    }
                    //���ư���
                    if(board[i][j].equals("white")){
                        g.drawImage(white,i*RATE+X_OFFSET,j*RATE+Y_OFFSET,null);
                    }
                }
            }
        }
    }
}
