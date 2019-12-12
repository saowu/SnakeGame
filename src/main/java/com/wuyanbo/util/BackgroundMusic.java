package com.wuyanbo.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;

/**
 * @description: 背景音乐
 * @author: wuyanbo
 * @create: 2019-12-08 13:55
 */

public class BackgroundMusic extends Thread {

    public Player player;

    public BackgroundMusic() {
        initPlayer(Config.bgmPath);
    }

    /**
     * 初始化音频播放器
     */
    void initPlayer(String path) {
        try {
            BufferedInputStream buffer = new BufferedInputStream(BackgroundMusic.class.getResourceAsStream(path));
            player = new Player(buffer);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //判断是否播放完毕且活着
        while (!player.isComplete() && Config.isLive) {
            //暂停线程
            if (Config.isLive && Config.isPause) {
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                //播放一帧
                player.play(1);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }


}
