package com.wuyanbo.frame;

import com.wuyanbo.panel.RegisterPanel;

import java.awt.*;

/**
 * @description: 注册窗口
 * @author: wuyanbo
 * @create: 2019-12-04 16:48
 */

public class RegisterFrame extends BaseFrame {


    private RegisterPanel registerPanel = new RegisterPanel(this);

    //添加无参构造器，创建窗体
    public RegisterFrame() throws HeadlessException {
        //调用用户添加组件的方法
        addComponents();
        //设置窗口显示
        this.setVisible(true);

    }

    /**
     * 用户添加组件的方法
     */
    private void addComponents() {
        this.add(registerPanel);

    }
}
