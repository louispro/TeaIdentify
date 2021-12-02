package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.listener.FailListener;
import com.louis.teaSystemClient.listener.SuccessListener;
import com.louis.teaSystemClient.pojo.ResultInfo;
import com.louis.teaSystemClient.util.GetUtils;
import com.louis.teaSystemClient.util.JsonUtils;
import com.louis.teaSystemClient.util.ResultInfoData2Vector;
import com.louis.teaSystemClient.util.UploadUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaBudIdentifyPanel extends JPanel{

    //模型列表
    JComboBox<String> modelCombox = new JComboBox<>();
    //识别按钮
    JButton identifyBtn = new JButton();
    JPanel originImagePanel = new JPanel();
    JPanel identifyImagePanel = new JPanel();
    //识别之后的图片真实路径
    private static final String REAL_PATH = "D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\identifyTeaBud";
    public TeaBudIdentifyPanel() {
        //整个panel设置borderlayout布局
        this.setLayout(new BorderLayout());
        this.loadModel();   //加载模型

        JPanel modelPanel = new JPanel();
        ImageIcon btnIcon = new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/run.png");
        identifyBtn.setBackground(new Color(203,220,217));
        identifyBtn.setBorder(BorderFactory.createLineBorder(new Color(203,220,217),0));
        identifyBtn.setIcon(btnIcon);
        modelPanel.setBackground(new Color(203,220,217));
        modelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        modelPanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
        modelPanel.add(modelCombox);
        modelPanel.add(identifyBtn);
        this.add(modelPanel,BorderLayout.NORTH);

        JPanel imagePanel = new JPanel();
        //imagePanel采用GridLayout布局
        imagePanel.setLayout(new GridLayout(1,2,1,0));
        originImagePanel.setLayout(new BorderLayout());
        this.setImage(originImagePanel,"D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\originTeaBud\\girl.jpg");

        originImagePanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
        identifyImagePanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
        //添加背景图片

        imagePanel.add(originImagePanel);
        imagePanel.add(identifyImagePanel);
        this.add(imagePanel);

        identifyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String realpath =  UploadUtil.uploadImage("http://localhost:8080/model/originTeaBud", new FailListener() {
                    @Override
                    public void fail() {
                        JOptionPane.showMessageDialog(imagePanel,"请求失败");
                    }
                });
                setImage(identifyImagePanel,realpath);
                identifyImagePanel.repaint();
            }
        });

    }

    public void loadModel(){
//        Box parent = this;
        GetUtils.get("http://localhost:8080/model/getModels", new SuccessListener() {
            @Override
            public void success(String result) {
                ResultInfo resultInfo = JsonUtils.parseResult(result);
                Vector<Vector> vectors = ResultInfoData2Vector.convertResultInfoData2Vector(resultInfo);
                for(Vector vector:vectors){
                    modelCombox.addItem((String)vector.get(1));
                }
            }
        }, new FailListener() {
            @Override
            public void fail() {
//                JOptionPane.showMessageDialog(parent,"网络异常，请检查网络");
            }
        });
    }

    //添加茶叶图片
    public void setImage(JPanel jPanel,String realPath){
//        Icon icon = new ImageIcon("src/main/resources/static/images/teaIdentify/water.jpg");
        JLabel jLabel = new JLabel();
        jLabel.setLayout(new BorderLayout());
        jLabel.setBorder(BorderFactory.createLineBorder(Color.green,2));
        ImageIcon image = new ImageIcon(realPath);
        Image scaleImage = image.getImage().getScaledInstance((int)(image.getIconWidth()*0.7),(int)(image.getIconHeight()*0.7),Image.SCALE_DEFAULT);
        image.setImage(scaleImage);
        jLabel.setIcon(image);
        jLabel.setHorizontalAlignment(JLabel.CENTER);   //图片居中
        jLabel.setVerticalAlignment(JLabel.CENTER);
//        System.out.println("width===>"+originImagePanel.getParent().getWidth());
//        System.out.println("height===>"+identifyImagePanel.getHeight());
        jPanel.add(jLabel,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("嫩芽识别");
        TeaBudIdentifyPanel teaBudIdentifyBox = new TeaBudIdentifyPanel();
        jFrame.add(teaBudIdentifyBox);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(850,500);
    }
}
