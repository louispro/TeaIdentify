package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.pojo.TeaImage;
import com.louis.teaSystemClient.util.ImageUtil;
import com.louis.teaSystemClient.util.PropertiesUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaBudProcessPanel extends JPanel {
    //背景色
    private static  final Color BG_COLOR = new Color(203,220,217);

    //茶叶图片对象
    private TeaImage teaImage = new TeaImage();

    //图片选择对话框
    private ImageFileChooser imageFileChooser = new ImageFileChooser("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\originTeaBud");


    //用于放置按钮的panel
    JPanel btnPanel = new JPanel();
    //放大按钮
    JButton biggerBtn = new JButton();
    //缩小按钮
    JButton smallerBtn = new JButton();
    //锐化按钮
    //模糊按钮

    
    //选择文件按钮
    JButton openImageBtn = new JButton();

    //用于放置图片和图片信息的Jpanel
    JPanel bottomPanel = new JPanel();

    //用于放置图片的panel
    JPanel imageJpanel = new JPanel();
    //用于放置图片的Label
    JLabel imageLabel = new JLabel();

    //用于显示图片信息的Jpanel
    JPanel imageInfoJpanel = new JPanel();
    //用于显示图片信息的JLabel
    JLabel imageInfoLabel = new JLabel();

    public TeaBudProcessPanel(){
        /*******************************************************
         *                  组装界面                            *
         *******************************************************/

        //整个panel设置borderlayout布局
        this.setLayout(new BorderLayout());

        //设置各个按钮的样式
        biggerBtn.setIcon(new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/bigger.png"));
        biggerBtn.setBackground(BG_COLOR);
        biggerBtn.setBorder(BorderFactory.createLineBorder(Color.gray,0));
        smallerBtn.setIcon(new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/smaller.png"));
        smallerBtn.setBackground(BG_COLOR);
        smallerBtn.setBorder(BorderFactory.createLineBorder(Color.gray,0));
        openImageBtn.setIcon(new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/directory.png"));
        openImageBtn.setBackground(BG_COLOR);
        openImageBtn.setBorder(BorderFactory.createLineBorder(Color.gray,0));
        

        //设置btnPanel样式和布局
        btnPanel.setLayout(new GridLayout(1,1,0,0));
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        //用于放置操作图片按钮的panel
        JPanel imageCtrJpanel = new JPanel();
        imageCtrJpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imageCtrJpanel.setBackground(BG_COLOR);
        imageCtrJpanel.add(biggerBtn);
        imageCtrJpanel.add(smallerBtn);
        //用于放置打开文件按钮的panel
        JPanel openFileJpanel = new JPanel();
        openFileJpanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        openFileJpanel.setBackground(BG_COLOR);
        openFileJpanel.add(openImageBtn);

        //btnpanel添加组件
        btnPanel.add(imageCtrJpanel);
        btnPanel.add(openFileJpanel);
        btnPanel.setBackground(BG_COLOR);

        //设置imageInfoJpanel/label样式和布局
        imageInfoLabel.setHorizontalAlignment(JLabel.LEFT);
        //添加图片信息
        imageInfoLabel.setText(teaImage.getInfo());
        //设置字体
        imageInfoLabel.setFont(new Font("宋体",Font.TRUETYPE_FONT,16));
        imageInfoJpanel.setLayout(new BorderLayout());
        imageInfoJpanel.add(imageInfoLabel,BorderLayout.WEST);
//        imageInfoJpanel.setBorder(BorderFactory.createLineBorder(Color.green));


        //设置imagePanel的样式和布局
        imageJpanel.setLayout(new BorderLayout());
        imageJpanel.add(imageLabel);
        //设置图片居中
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        //设置图片
        imageLabel.setIcon(teaImage.getImageIcon());
        //往bottomPanel中添加组件
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(imageInfoJpanel,BorderLayout.NORTH);
        bottomPanel.add(imageJpanel);

        //往TeaBudProcessPanel中添加组件
        this.add(btnPanel,BorderLayout.NORTH);
        this.add(bottomPanel);
//        this.add(bottomBox);


        /********************************************
         *               监听按钮事件                     *
         ********************************************/

        //放大按钮事件
        biggerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                teaImage.setRate(teaImage.getRate().add(new BigDecimal("0.05")));
                teaImage.setImageIcon();
                imageLabel.setIcon(teaImage.getImageIcon());
                imageLabel.repaint();
            }
        });

        //缩小按钮事件
        smallerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                teaImage.setRate(teaImage.getRate().subtract(new BigDecimal("0.05")));
                teaImage.setImageIcon();
                imageLabel.setIcon(teaImage.getImageIcon());
                imageLabel.repaint();
            }
        });

        //打开文件事件
        openImageBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = imageFileChooser.showOpenDialog(imageJpanel);
                if(result == JFileChooser.APPROVE_OPTION){
                    teaImage.setAll(imageFileChooser.getSelectedFile().getPath());
                    imageLabel.setIcon(teaImage.getImageIcon());
                    imageInfoLabel.setText(teaImage.getInfo());
                }
            }
        });

        //用于检测被选择文件的改变事件，预览图片
        imageFileChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //选择的文件发生了改变
                if(evt.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
                    //获取用户选择的新文件
                    File f = (File) evt.getNewValue();
                    if(f == null){
                        //没选择文件什么都不做
                        return;
                    }
                    //将所选择的文件读入ImageIcon对象中
                    ImageIcon icon = new ImageIcon(f.getPath());
                    //如果图像太大，则缩小他
                    if(icon.getIconWidth() > imageFileChooser.getPREVIEW_SIZE()){
                        icon = new ImageIcon(icon.getImage().getScaledInstance(imageFileChooser.getPREVIEW_SIZE(),-1,Image.SCALE_DEFAULT));
                    }
                    imageFileChooser.getAccessory().setIcon(icon);
                }
            }
        });

    }


    public static void main(String[] args) {
        JFrame jf = new JFrame("Process");
        jf.setSize(600,600);
        TeaBudProcessPanel processPanel = new TeaBudProcessPanel();
        jf.add(processPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
