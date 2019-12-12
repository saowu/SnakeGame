package com.wuyanbo.frame;

import com.wuyanbo.panel.ButtonPanel;
import com.wuyanbo.panel.SnakePanel;

import java.awt.*;

/**
 * @description: 游戏界面
 * @author: wuyanbo
 * @create: 2019-12-04 11:24
 */

public class SnakeFrame extends BaseFrame {


    private SnakePanel snakePanel = new SnakePanel();

    private ButtonPanel buttonPanel = new ButtonPanel(snakePanel);


    public SnakeFrame() throws HeadlessException {
        //调用用户添加组件的方法
        addComponents();
        //设置窗口显示
        this.setVisible(true);
    }

    /**
     * 用户添加组件的方法
     */
    private void addComponents() {
        this.add(snakePanel);
        this.add(buttonPanel);
    }

}
