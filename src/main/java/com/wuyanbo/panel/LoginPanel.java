package com.wuyanbo.panel;

import com.wuyanbo.dao.UserDao;
import com.wuyanbo.frame.RegisterFrame;
import com.wuyanbo.frame.SnakeFrame;
import com.wuyanbo.util.Config;
import com.wuyanbo.util.ImageUtil;
import com.wuyanbo.util.Music;

import javax.swing.*;
import java.awt.*;

/**
 * @description: 登录窗体Panel
 * @author: wuyanbo
 * @create: 2019-12-03 10:23
 */

public class LoginPanel extends JPanel {

    private JFrame currentFrame;
    private Image bg_image;
    private JLabel jl_username;
    private JLabel jl_password;
    private JTextField jtf_username;
    private JPasswordField jpf_password;
    private JButton jbtn_login;
    private JButton jbtn_regis;
    private static Music music;


    /**
     * 创建面板对象的构造方法
     */
    public LoginPanel(JFrame currentFrame) {
        this.currentFrame = currentFrame;
        //初始化面板
        initPanel();
        //初始化组件信息
        initComponents();
        //添加组件
        addComponents();
        //绑定事件监听
        bindActions();
    }

    /**
     * 绑定事件监听
     */
    private void bindActions() {
        //登录按钮监听
        jbtn_login.addActionListener((actionEvent) -> {
            //获得用户名密码
            String username = jtf_username.getText();
            String password = new String(jpf_password.getPassword());
            //根据用户名密码比较
            Config.user = new UserDao().login(username, password);
            //成功并跳转窗口
            if (Config.user != null) {
                JOptionPane.showMessageDialog(this, "登录成功");
                //关闭当前窗口
                currentFrame.dispose();
                //关闭音乐
                if (music.isAlive()) {
                    //有待解决替代启用方法
                    music.stop();
                }
                //开启游戏窗口
                new SnakeFrame();
            } else {
                jtf_username.setText("");
                jpf_password.setText("");
                JOptionPane.showMessageDialog(this, "登录失败");
            }
        });
        //注册
        jbtn_regis.addActionListener((actionEvent) -> {
            new RegisterFrame();
            currentFrame.dispose();
        });
    }

    /**
     * 初始化组件信息
     */
    private void addComponents() {
        this.add(jl_username);
        this.add(jl_password);
        this.add(jtf_username);
        this.add(jpf_password);
        this.add(jbtn_login);
        this.add(jbtn_regis);

    }

    /**
     * 初始化组件信息
     */
    private void initComponents() {

        bg_image = ImageUtil.getImage("/img/bg_login.png");
        if (music == null) {
            music = new Music(Config.loginPath);
            music.start();
        }
        jl_username = new JLabel("用户名：");
        jl_password = new JLabel("密  码：");
        jtf_username = new JTextField(16);
        jpf_password = new JPasswordField(16);
        jbtn_login = new JButton("登录");
        jbtn_regis = new JButton("注册");
        //设置上述组件位置
        jl_username.setBounds(500, 200, 100, 50);
        jl_password.setBounds(500, 250, 100, 50);
        jtf_username.setBounds(550, 215, 100, 20);
        jpf_password.setBounds(550, 265, 100, 20);
        jbtn_login.setBounds(500, 300, 60, 30);
        jbtn_regis.setBounds(600, 300, 60, 30);
    }

    /**
     * 初始化面板信息
     */
    private void initPanel() {
        //设置面板位置
        this.setBounds(0, 0, 700, 500);
        //设置背景色
        //this.setBackground(Color.GREEN);
        //设置布局
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //绘制背景图片
        g.drawImage(bg_image, 0, 0, this);
    }


}
