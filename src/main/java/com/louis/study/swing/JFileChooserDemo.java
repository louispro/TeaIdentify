package com.louis.study.swing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @赖小燚
 * @www.louis_lai.com
 */
public class JFileChooserDemo {

    JFrame jFrame = new JFrame("文件选择器");

    JFileChooser jFileChooser = new JFileChooser(".");

    JMenuBar jMenuBar = new JMenuBar();

    JMenu file = new JMenu("文件");

    JMenuItem open = new JMenuItem(new AbstractAction("打开") {
        @Override
        public void actionPerformed(ActionEvent e) {
            jFileChooser.showOpenDialog(jFrame);

            File imageFile = jFileChooser.getSelectedFile();
            File file = jFileChooser.getSelectedFile();
            try{
                image = ImageIO.read(imageFile);
                myCanvas.repaint();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    });

    JMenuItem save = new JMenuItem(new AbstractAction("另存为") {
        @Override
        public void actionPerformed(ActionEvent e) {
            jFileChooser.showSaveDialog(jFrame);
            File file = jFileChooser.getSelectedFile();
            try{
                ImageIO.write(image,"jpg",file);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    });

    BufferedImage image;
    private class MyCanvas extends JPanel{
        @Override
        public void paint(Graphics g) {
            if(image!=null){
                g.drawImage(image,0,0,null);
            }
        }
    }

    MyCanvas myCanvas = new MyCanvas();

    public void init(){
        jFrame.setJMenuBar(jMenuBar);
        jFrame.add(myCanvas);
        myCanvas.setPreferredSize(new Dimension(500,300));
        jMenuBar.add(file);
        file.add(open);
        file.add(save);

        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new JFileChooserDemo().init();
    }
}
