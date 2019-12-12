package com.wuyanbo.util;

/**
 * @description: 方向类
 * @author: wuyanbo
 * @create: 2019-12-05 11:25
 */

public enum Direction {

    UP("上"),
    DOWN("下"),
    LEFT("左"),
    RIGHT("右");

    private String name;

    Direction(String name) {
        this.name = name;
    }
}

