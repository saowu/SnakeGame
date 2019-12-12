package com.wuyanbo.po;

import com.wuyanbo.util.Config;
import com.wuyanbo.util.Direction;
import com.wuyanbo.util.ImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @description: 蛇身结点
 * @author: wuyanbo
 * @create: 2019-12-05 11:05
 */

public class Node {
    //行
    int row;
    //列
    int col;
    //前进方向 ↑ ↓ ← → u d l r
    Direction dir;
    //上一个
    Node pre;
    //上一个
    Node next;

    private static int i = 1;

    public Node(int row, int col, Direction dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }

    /**
     * 绘制结点
     * @param g
     * @param p
     */
    public void draw(Graphics g, JPanel p) {
        if (i > 4) {
            i = 1;
        }
        g.drawImage(ImageUtil.getImage("/icon/" + (i++) + ".png"), this.col * Config.SPAN, this.row * Config.SPAN, p);
    }

}
