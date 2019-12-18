package com.wuyanbo.po;

import com.wuyanbo.util.Config;
import com.wuyanbo.util.Direction;
import com.wuyanbo.util.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @description: 蛇类
 * @author: wuyanbo
 * @create: 2019-12-05 11:01
 */

public class Snake {
    //蛇头
    public Node head;
    //蛇身
    public Node body;
    //蛇尾
    public Node tail;
    //速度
    public long speech;

    public Snake() {

        //实例化
        head = new Node(13, 13, Direction.UP);
        body = new Node(13, 14, Direction.UP);
        tail = new Node(13, 15, Direction.UP);

        head.next = body;
        body.next = tail;

        tail.pre = body;
        body.pre = head;

        speech = 400;
    }

    /**
     * 描绘蛇身
     *
     * @param g
     */
    public void draw(Graphics g, JPanel p) {
        Node node = head;
        while (node != null) {
            node.draw(g,p);
            node = node.next;
        }
    }

    /**
     * 蛇身移动
     *
     * @param foot
     */
    public synchronized void move(Foot foot) {
        if (Config.isLive) {
            //添加头结点
            addHead();
            //判断是否吃到食物,如没吃到，移除蛇尾,否则食物重置
            if (isEat(foot)) {
                //播放吃声
                startSound();
                //成绩增加
                foot.score++;
                //重置食物
                foot.repair(this);
                //速度增加
                increaseSpeed(foot);
            } else {
                removeTail();
            }
            //判断死亡
            isDead();
            if (isDead()) {
                Config.isLive = false;
            }
        }
    }

    /**
     * 播放叫声
     */
    private void startSound() {
        new Music(Config.eatPath).start();
    }

    /**
     * 速度增加
     */
    private void increaseSpeed(Foot foot) {
        if (speech > 240) {
            if (foot.score % 5 == 0) {
                speech -= 10;
            }
        }
    }

    /**
     * 判断死亡
     */
    private boolean isDead() {
        //判断是否超出界限
        boolean isLimit = (head.row >= Config.ROWS) || (head.row < 0) || (head.col < 0) || (head.col >= Config.COLS);
        //判断是否撞蛇身
        boolean isCollision = isCollision();
        return (isLimit || isCollision);
    }

    /**
     * 判断蛇身碰撞
     */
    private boolean isCollision() {
        boolean result = false;
        Node temp = body;
        while (temp != null) {
            if (head.row == temp.row && head.col == temp.col) {
                result = true;
                return result;
            }
            temp = temp.next;
        }
        return result;
    }


    /**
     * 判断是否吃到食物
     *
     * @param foot
     */
    private boolean isEat(Foot foot) {
        if (foot.row != head.row || foot.col != head.col) {
            return false;
        }
        return true;
    }

    /**
     * 移除尾巴
     */
    private void removeTail() {
        tail = tail.pre;
        tail.next = null;
    }

    /**
     * 添加蛇头
     */
    private void addHead() {

        Node node = null;
        switch (head.dir) {
            case UP:
                node = new Node(head.row - 1, head.col, head.dir);
                break;
            case DOWN:
                node = new Node(head.row + 1, head.col, head.dir);
                break;
            case LEFT:
                node = new Node(head.row, head.col - 1, head.dir);
                break;
            case RIGHT:
                node = new Node(head.row, head.col + 1, head.dir);
                break;
            default:
                break;
        }
        head.pre = node;
        node.next = head;
        body = head;
        head = head.pre;
    }

    /**
     * 蛇头方向控制
     *
     * @param e
     */
    public boolean dirControl(KeyEvent e) {
        //是否逆方向标记
        boolean flag = true;

        boolean isHUD = head.dir.equals(Direction.DOWN) || head.dir.equals(Direction.UP);
        boolean isHLR = head.dir.equals(Direction.RIGHT) || head.dir.equals(Direction.LEFT);
        //1.解决蛇头方向快速切换的bug，取消连续二次按键,损失灵敏性->2.SnakePanel 40line
        //boolean isBUD = body.dir.equals(Direction.DOWN) || body.dir.equals(Direction.UP);
        //boolean isBLR = body.dir.equals(Direction.RIGHT) || body.dir.equals(Direction.LEFT);

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!isHUD) {
                    head.dir = Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!isHUD) {
                    head.dir = Direction.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!isHLR) {
                    head.dir = Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!isHLR) {
                    head.dir = Direction.RIGHT;
                }
                break;
            default:
                break;
        }
        //解决逆方向加速
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (head.dir == Direction.DOWN) {
                    flag = false;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (head.dir == Direction.UP) {
                    flag = false;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (head.dir == Direction.RIGHT) {
                    flag = false;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (head.dir == Direction.LEFT) {
                    flag = false;
                }
                break;
            default:
                break;
        }
        return flag;
    }
}
