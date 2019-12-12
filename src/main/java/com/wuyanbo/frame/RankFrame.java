package com.wuyanbo.frame;

import com.wuyanbo.panel.RankPanel;

import java.awt.*;

/**
 * @description: 排行窗口
 * @author: wuyanbo
 * @create: 2019-12-06 14:02
 */

public class RankFrame extends BaseFrame {


    private RankPanel rankPanel = new RankPanel();

    public RankFrame() throws HeadlessException {
        //调用用户添加组件的方法
        addComponents();
        //仅关闭窗口
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //设置窗口显示
        this.setVisible(true);

    }

    /**
     * 用户添加组件的方法
     */
    private void addComponents() {
        this.add(rankPanel);

    }
}
