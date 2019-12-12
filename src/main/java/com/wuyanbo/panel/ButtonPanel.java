package com.wuyanbo.panel;

import com.wuyanbo.frame.RankFrame;
import com.wuyanbo.po.Foot;
import com.wuyanbo.po.Snake;
import com.wuyanbo.util.Config;

import javax.swing.*;

/**
 * @description: 按钮面板
 * @author: wuyanbo
 * @create: 2019-12-05 08:35
 */

public class ButtonPanel extends JPanel {

    private SnakePanel snakePanle;
    private JButton btn_pause;
    private JButton btn_continue;
    private JButton btn_restart;
    private JButton btn_rank;


    public ButtonPanel(SnakePanel snakePanel) {
        this.snakePanle = snakePanel;
        initPanel();
        initComponents();
        addComponents();
        bindActions();

    }

    /**
     * 绑订事件
     */
    private void bindActions() {
        //暂停
        btn_pause.addActionListener((actionEvent) -> {
            pause();
        });
        //继续
        btn_continue.addActionListener((actionEvent) -> {
            continues();
        });
        //重新开始
        btn_restart.addActionListener((actionEvent) -> {
            pause();
            JOptionPane.showMessageDialog(snakePanle, "重新游戏");
            restart();
        });
        //排行
        btn_rank.addActionListener((actionEvent) -> {
            //暂停游戏
            pause();
            //打开窗口
            new RankFrame();
        });
    }


    /**
     * 添加组件
     */
    private void addComponents() {
        this.add(btn_pause);
        this.add(btn_continue);
        this.add(btn_restart);
        this.add(btn_rank);
    }

    /**
     * 初始化组件
     */
    private void initComponents() {
        btn_pause = new JButton("暂停游戏");
        btn_continue = new JButton("继续游戏");
        btn_restart = new JButton("重新开始");
        btn_rank = new JButton("游戏排行");
    }

    /**
     * 初始化panel信息
     */
    private void initPanel() {
        this.setBounds(0, 440, 700, 60);
    }

    /**
     * 暂停游戏
     */
    private void pause() {
        if (Config.isLive) {
            if (!Config.isPause) {
                Config.isPause = true;
            }
        }
    }

    /**
     * 继续游戏
     */
    private void continues() {
        if (Config.isLive) {
            if (Config.isPause) {
                //唤醒游戏子线程
                synchronized (snakePanle.snakeThead) {
                    snakePanle.snakeThead.notify();
                }
                //唤醒音乐子线程
                synchronized (snakePanle.backgroundMusic) {
                    snakePanle.backgroundMusic.notify();
                }
                Config.isPause = false;
            }
            snakePanle.requestFocus();
        }
    }


    /**
     * 重新开始
     */
    private void restart() {
        snakePanle.foot = new Foot();
        snakePanle.snake = new Snake();
        snakePanle.startGame();
        continues();
    }


}
