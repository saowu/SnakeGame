package com.wuyanbo.frame;

import com.wuyanbo.util.ImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author: wuyanbo
 * @create: 2019-12-04 11:32
 */

public abstract class BaseFrame extends JFrame {

    public BaseFrame() throws HeadlessException {
        initFrame();
    }

    /**
     * 初始化窗体
     */
    public void initFrame() {
        //设置窗口显示位置及大小
        this.setBounds(300, 50, 706, 500);
        //设置标题
        this.setTitle("贪吃火影");
        //设置图标
        this.setIconImage(ImageUtil.getImage("/img/logo.png"));
        //设置布局
        this.setLayout(null);
        //设置窗口不可调节大小
        this.setResizable(false);
        //设置关闭窗口，程序终止
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
