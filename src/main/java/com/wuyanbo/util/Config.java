package com.wuyanbo.util;

import com.wuyanbo.po.User;

import java.text.SimpleDateFormat;

/**
 * @description: 配置信息
 * @author: wuyanbo
 * @create: 2019-12-05 09:45
 */

public class Config {

    //游戏面板
    public static final int ROWS = 22;
    public static final int COLS = 35;
    public static final int SPAN = 20;
    //蛇存活状态
    public static boolean isLive = false;
    //游戏状态
    public static boolean isPause = false;
    //登录用户
    public static User user = null;
    //背景音乐
    public static String bgmPath = "/mp3/giao.mp3";
    public static String eatPath = "/mp3/eat.mp3";
    public static String loginPath = "/mp3/login.mp3";
    public static String endPath = "/mp3/end.mp3";
    //时间格式
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
}
