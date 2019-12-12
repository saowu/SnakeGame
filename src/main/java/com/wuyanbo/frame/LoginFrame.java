package com.wuyanbo.frame;

import com.wuyanbo.panel.LoginPanel;

import java.awt.*;

/**
 * @description: 登录界面
 * @author: wuyanbo
 * @create: 2019-12-03 09:05
 **/

public class LoginFrame extends BaseFrame {

    //面板对象
    private LoginPanel loginPanel = new LoginPanel(this);

    //添加无参构造器，创建窗体
    public LoginFrame() throws HeadlessException {
        //调用用户添加组件的方法
        addComponents();
        //设置窗口显示
        this.setVisible(true);
    }

    /**
     * 用户添加组件的方法
     */
    private void addComponents() {
        this.add(loginPanel);

    }
}
