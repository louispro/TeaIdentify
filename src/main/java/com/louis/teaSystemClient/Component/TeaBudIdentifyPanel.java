package com.louis.teaSystemClient.Component;

import com.louis.teaSystemClient.filter.ImageFileFilter;
import com.louis.teaSystemClient.listener.FailListener;
import com.louis.teaSystemClient.listener.SuccessListener;
import com.louis.teaSystemClient.pojo.ResultInfo;
import com.louis.teaSystemClient.render.FileIconView;
import com.louis.teaSystemClient.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Vector;

/**
 * 赖小燚
 * www.louis.com
 */
public class TeaBudIdentifyPanel extends JPanel{

    //模型列表
    JComboBox<String> modelCombox = new JComboBox<>();
    //顶部的两个panel
    JPanel modelPanel = new JPanel();
    JPanel btnPanel = new JPanel();     //此panel用于放置按钮

    //识别按钮
    JButton identifyBtn = new JButton();

    //选择文件按钮
    JButton chooseImageBtn = new JButton();

    //文件选择器
    JFileChooser jFileChooser = new JFileChooser("D:\\Codes\\JavaCode\\TeaSystem\\src\\main\\resources\\static\\images\\teaIdentify\\client\\originTeaBud");

    //预览图片
    JLabel accessory = new JLabel();
    //图片预览组件的大小
    final int PREVIEW_SIZE = 200;

    //用于盛放图片的panel和label
    JPanel originImagePanel = new JPanel();
    JPanel identifyImagePanel = new JPanel();
    JLabel originImageLabel = new JLabel();
    JLabel identifyImageLabel = new JLabel();

    //当前待识别图片的路径
    private String currentTeaImagePath = PropertiesUtil.getValue("currentTeaImagePath");


    public TeaBudIdentifyPanel() {
        //整个panel设置borderlayout布局
        this.setLayout(new BorderLayout());
        this.loadModel();   //加载模型

        JPanel topPanel = new JPanel();

        //设置识别按钮样式
        ImageIcon btnIcon = new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/run.png");
        identifyBtn.setBackground(new Color(203,220,217));
        identifyBtn.setBorder(BorderFactory.createLineBorder(new Color(203,220,217),0));
        identifyBtn.setIcon(btnIcon);
        //设置文件选择按钮样式
        ImageIcon fileIcon = new ImageIcon("src/main/resources/static/images/teaIdentify/client/tea/directory.png");
        chooseImageBtn.setBackground(new Color(203,220,217));
        chooseImageBtn.setBorder(BorderFactory.createLineBorder(new Color(203,220,217),0));
        chooseImageBtn.setIcon(fileIcon);


        //设置topPanel样式和布局
        topPanel.setLayout(new GridLayout(1,1,0,0));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
        modelPanel.setBackground(new Color(203,220,217));
        btnPanel.setBackground(new Color(203,220,217));
        modelPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,13));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));

        //topPanel添加组件
        topPanel.add(modelPanel);
        topPanel.add(btnPanel);
        modelPanel.add(modelCombox);
        btnPanel.add(identifyBtn);
        btnPanel.add(chooseImageBtn);

        //设置文件选择器样式
        initFileChooser(jFileChooser);

        //添加按钮等组件
        this.add(topPanel,BorderLayout.NORTH);

        /*****************                           组装识别视图                                                  **************/
        //组装显示图片的组件
        JPanel imagePanel = new JPanel();
        //imagePanel采用GridLayout布局
        imagePanel.setLayout(new GridLayout(1,2,1,0));

        //设置panel样式和布局
        originImagePanel.setLayout(new BorderLayout());
        identifyImagePanel.setLayout(new BorderLayout());
        this.setImage(originImageLabel,currentTeaImagePath);
        originImagePanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
        identifyImagePanel.setBorder(BorderFactory.createLineBorder(Color.gray,1));

        //设置label样式和布局
        originImageLabel.setHorizontalAlignment(JLabel.CENTER);
        originImageLabel.setVerticalAlignment(JLabel.CENTER);
        identifyImageLabel.setHorizontalAlignment(JLabel.CENTER);
        identifyImageLabel.setVerticalAlignment(JLabel.CENTER);
        originImageLabel.setBorder(BorderFactory.createLineBorder(Color.green,2));
        identifyImageLabel.setBorder(BorderFactory.createLineBorder(Color.green,2));
        //将label放入panel中
        originImagePanel.add(originImageLabel,BorderLayout.CENTER);
        identifyImagePanel.add(identifyImageLabel,BorderLayout.CENTER);
        //添加背景图片组件的panel
        imagePanel.add(originImagePanel);
        imagePanel.add(identifyImagePanel);
        this.add(imagePanel);

        //识别图片
        identifyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //将当前图片currentTeaImagePath上传给服务器识别
                String realpath =  UploadUtil.uploadImage("http://localhost:8080/model/identify",currentTeaImagePath, new FailListener() {
                    @Override
                    public void fail() {
                        JOptionPane.showMessageDialog(imagePanel,"请求失败");
                        return;
                    }
                });
                setImage(identifyImageLabel,realpath);
            }
        });

        //打开文件选择器选择文件
        chooseImageBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = jFileChooser.showOpenDialog(imagePanel);
                if(result == JFileChooser.APPROVE_OPTION){
                    //获取当前选择的文件的完整路径
                    currentTeaImagePath = jFileChooser.getSelectedFile().getPath();
                    setImage(originImageLabel,currentTeaImagePath);
                }
            }
        });

        //用于检测被选择文件的改变事件，预览图片
        jFileChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //选择的文件发生了改变
                if(evt.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
                    //获取用户选择的新文件
                    File f = (File) evt.getNewValue();
                    PropertiesUtil.setValue("currentTeaImagePath",f.getAbsolutePath());
                    if(f == null){
                        //没选择文件什么都不做
                        return;
                    }
                    //将所选择的文件读入ImageIcon对象中
                    ImageIcon icon = new ImageIcon(f.getPath());
                    //如果图像太大，则缩小他
                    if(icon.getIconWidth() > PREVIEW_SIZE){
                        icon = new ImageIcon(icon.getImage().getScaledInstance(PREVIEW_SIZE,-1,Image.SCALE_DEFAULT));
                    }
                    accessory.setIcon(icon);
                }
            }
        });
    }

    public void loadModel(){
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
                //网络异常无法获取model信息
                String models = PropertiesUtil.getValue("model");
                for(String model:models.split(",")){
                    modelCombox.addItem(model);
                }
            }
        });
    }

    //添加茶叶图片
    public void setImage(JLabel jLabel,String realPath){
        //
        jLabel.setBorder(BorderFactory.createLineBorder(Color.green,2));
        ImageIcon image = new ImageIcon(realPath);
        //缩小图片
        Image scaleImage = ImageUtil.scaleImageSize(realPath);
        image.setImage(scaleImage);
        jLabel.setIcon(image);
    }

    //初始化文件选择器
    public JFileChooser initFileChooser(JFileChooser fileChooser){
        //图片文件过滤器
        ImageFileFilter imageFileFilter = new ImageFileFilter();
        imageFileFilter.addExtension("jpg");
        imageFileFilter.addExtension("png");
        imageFileFilter.addExtension("jpeg");
        imageFileFilter.addExtension("gif");
        imageFileFilter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");
        //添加文件过滤器
        fileChooser.addChoosableFileFilter(imageFileFilter);
        //禁止文件类型下拉列表中显示所有文件选项
        fileChooser.setAcceptAllFileFilterUsed(false);
        //为文件选择器指定自定义的FileView对象
        fileChooser.setFileView(new FileIconView(imageFileFilter));
        //设置文件选择框标题
        fileChooser.setDialogTitle("选择图片");
        //为文件选择器指定一个预览图片的附件
        fileChooser.setAccessory(accessory);
        accessory.setPreferredSize(new Dimension(200,200));
        accessory.setBorder(BorderFactory.createEtchedBorder());
        return fileChooser;
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
