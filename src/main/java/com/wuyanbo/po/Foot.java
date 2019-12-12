package com.wuyanbo.po;

import com.wuyanbo.util.Config;
import com.wuyanbo.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @description: 食物类
 * @author: wuyanbo
 * @create: 2019-12-05 10:16
 */

public class Foot {

    public int row;

    public int col;

    public int score;

    private static int i = 5;

    /**
     * 随机产生行列
     */
    public void repair(Snake snake) {
        row = new Random().nextInt(Config.ROWS);
        col = new Random().nextInt(Config.COLS);
        //如果产生食物在蛇身上就重置
        Node temp = snake.head;
        while (temp != null) {
            if (row == temp.row && col == temp.col) {
                repair(snake);
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 绘制食物
     *
     * @param g
     * @param p
     */
    public void draw(Graphics g, JPanel p) {
        if (i > 10) {
            i = 5;
        }
        g.drawImage(ImageUtil.getImage("/icon/" + (i++) + ".png"), this.col * Config.SPAN, this.row * Config.SPAN, p);
    }

    /**
     * 绘制成绩
     *
     * @param g
     * @param font
     */
    public void drawScore(Graphics g, Font font) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("成绩:" + score, 40, 40);
    }


}
