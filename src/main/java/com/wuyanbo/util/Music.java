package com.wuyanbo.util;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;

/**
 * @description: 播一遍音频
 * @author: wuyanbo
 * @create: 2019-12-11 09:08
 */

public class Music extends Thread {

    public Player player;

    public Music(String path) {
        initPlayer(path);
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
        try {
            //播放音效
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}
