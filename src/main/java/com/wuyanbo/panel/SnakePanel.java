package com.wuyanbo.panel;

import com.wuyanbo.dao.RankDao;
import com.wuyanbo.po.Foot;
import com.wuyanbo.po.Rank;
import com.wuyanbo.po.Snake;
import com.wuyanbo.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @description: 游戏面板
 * @author: wuyanbo
 * @create: 2019-12-05 08:35
 */

public class SnakePanel extends JPanel {

    private Font font;
    private Image bg_img;
    //游戏线程
    SnakeThead snakeThead;
    //背景音乐线程
    BackgroundMusic backgroundMusic;
    //食物实体
    Foot foot = new Foot();
    //蛇实体
    Snake snake = new Snake();


    public SnakePanel() {
        initPanel();
        //初始化组件信息
        initComponents();
        //添加组件
        addComponents();
        //绑定事件监听
        bindActions();
        //游戏线程类启动
        startGame();

    }

    /**
     * 绑订事件
     */
    private void bindActions() {
        //给当前面板设置键盘事件监听
        this.addKeyListener(new KeyListener() {
            //键入事件，输入字符时调用
            @Override
            public void keyTyped(KeyEvent e) {

            }

            //键盘按下的时候
            @Override
            public void keyPressed(KeyEvent e) {
                boolean flag = snake.dirControl(e);
                //2.解决蛇头方向连续变化bug，灵敏性高，长按加速！-> Snake 147 line
                if (flag) {
                    snake.move(foot);
                    repaint();
                }
            }

            //键盘已释放的时候
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * 添加组件
     */
    private void addComponents() {
    }

    /**
     * 初始化组件
     */
    private void initComponents() {

        bg_img = ImageUtil.getImage("/img/bg_game.png");
        font = new Font("黑体", Font.BOLD, 30);
    }

    /**
     * 初始化panel信息
     */
    private void initPanel() {
        this.setBounds(0, 0, 700, 440);
        this.setBackground(Color.YELLOW);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制背景
        g.drawImage(bg_img, 0, 0, this);
        //绘制食物
        foot.draw(g,this);
        //绘制成绩
        foot.drawScore(g, font);
        //绘制蛇
        snake.draw(g,this);
    }

    /**
     * 启动游戏线程
     */
    public void startGame() {
        //初始化食物
        foot.repair(snake);
        //蛇是否存活
        if (!Config.isLive) {
            Config.isLive = true;
            //开启音乐线程
            backgroundMusic = new BackgroundMusic();
            backgroundMusic.start();
            //开启游戏线程
            snakeThead = new SnakeThead();
            snakeThead.start();
            //存活状态下可获得焦点
            this.setFocusable(true);
            this.requestFocus();
        }
    }

    /**
     * 贪吃蛇线程类
     */
    class SnakeThead extends Thread {
        @Override
        public void run() {
            while (Config.isLive) {
                //音乐是否结束
                if (!backgroundMusic.isAlive()) {
                    backgroundMusic = new BackgroundMusic();
                    backgroundMusic.start();
                }
                //刷新频率
                try {
                    Thread.sleep(snake.speech);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //游戏暂停
                synchronized (this) {
                    if (Config.isPause) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //移动
                snake.move(foot);
                //重绘制
                repaint();
            }
            //死亡不可获得焦点
            SnakePanel.this.setFocusable(false);
            //播放结束音效
            new Music(Config.endPath).start();
            //保存游戏记录
            Rank rank = new RankDao().insert(new Rank(foot.score, Config.user));
            if (rank != null) {
                JOptionPane.showMessageDialog(SnakePanel.this, "游戏结束", "消息", JOptionPane.WHEN_IN_FOCUSED_WINDOW);
            }
        }

    }


}
