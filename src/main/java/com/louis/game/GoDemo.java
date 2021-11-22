package com.louis.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

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

    //�û��͵����Ƿ�����
    private boolean isUserGo = false;
    private boolean isRobotGo = false;

    //��ʱ�����û�����һ��֮���������
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //�û�����֮�������
            if(isUserGo){
                //��������
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
                //�û�����
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

            //����˳����̺󣬸�λѡ�е�����
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
            if(selectX >= 0 && selectY >= 0){
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
