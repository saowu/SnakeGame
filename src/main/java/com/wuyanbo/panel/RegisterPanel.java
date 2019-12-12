package com.wuyanbo.panel;

import com.wuyanbo.dao.UserDao;
import com.wuyanbo.frame.LoginFrame;
import com.wuyanbo.po.User;
import com.wuyanbo.util.ImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @description: 注册容器
 * @author: wuyanbo
 * @create: 2019-12-04 16:49
 */

public class RegisterPanel extends JPanel {

    private JFrame currentFrame;
    private Image bg_image;
    private JLabel jl_username;
    private JLabel jl_password;
    private JLabel jl_password2;
    private JTextField jtf_username;
    private JPasswordField jpf_password;
    private JPasswordField jpf_password2;
    private JButton jbtn_registered;
    private JButton jbtn_back;


    /**
     * 创建面板对象的构造方法
     */
    public RegisterPanel(JFrame currentFrame) {
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
        //注册按钮
        jbtn_registered.addActionListener((actionEvent) -> {
            String username = jtf_username.getText();
            String passwd = new String(jpf_password.getPassword());
            String passwd2 = new String(jpf_password2.getPassword());

            UserDao userDao = new UserDao();
            User user = userDao.selectUser(username);

            if (user == null) {
                if (passwd.equals(passwd2)) {
                    //判断是否包含字母和数字
                    if (passwd.matches(".*[a-zA-z].*") && passwd.matches(".*[0-9].*")) {
                        User registered = userDao.registered(new User(username, passwd));
                        if (registered != null) {
                            JOptionPane.showMessageDialog(this, "注册成功");

                            currentFrame.dispose();

                            new LoginFrame();
                        } else {
                            JOptionPane.showMessageDialog(this, "注册失败");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "密码需同时包含字母和数字");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "密码不同");
                }
            } else {
                JOptionPane.showMessageDialog(this, "已被注册");
                jtf_username.setText("");
                jpf_password.setText("");
                jpf_password2.setText("");
            }
        });
        //返回按钮
        jbtn_back.addActionListener((actionEvent) -> {
            currentFrame.dispose();
            new LoginFrame();
        });
    }

    /**
     * 初始化组件信息
     */
    private void addComponents() {
        this.add(jl_username);
        this.add(jl_password);
        this.add(jl_password2);
        this.add(jtf_username);
        this.add(jpf_password);
        this.add(jpf_password2);
        this.add(jbtn_registered);
        this.add(jbtn_back);
    }

    /**
     * 初始化组件信息
     */
    private void initComponents() {
        bg_image = ImageUtil.getImage("/img/bg_regis.png");

        jl_username = new JLabel("用户名：");
        jl_password = new JLabel("密  码：");
        jl_password2 = new JLabel("验证密码：");
        jtf_username = new JTextField(16);
        jpf_password = new JPasswordField(16);
        jpf_password2 = new JPasswordField(16);
        jbtn_registered = new JButton("提交");
        jbtn_back = new JButton("回退");

        //设置上述组件位置
        jl_username.setBounds(500, 200, 100, 50);
        jl_password.setBounds(500, 250, 100, 50);
        jl_password2.setBounds(500, 300, 100, 50);
        jtf_username.setBounds(555, 215, 100, 20);
        jpf_password.setBounds(555, 265, 100, 20);
        jpf_password2.setBounds(555, 315, 100, 20);
        jbtn_registered.setBounds(500, 365, 60, 30);
        jbtn_back.setBounds(600, 365, 60, 30);

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
