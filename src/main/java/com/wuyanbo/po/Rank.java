package com.wuyanbo.po;

import com.wuyanbo.util.Config;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @description: 排行
 * @author: wuyanbo
 * @create: 2019-12-03 13:53
 */

public class Rank {
    private Integer id;
    private Integer score;
    private Timestamp date;
    private User user;


    public Rank() {
        date = new Timestamp(new Date().getTime());
    }

    public Rank(Integer score, User user) {
        this.score = score;
        this.date = new Timestamp(new Date().getTime());
        this.user = user;
    }


    public Rank(Integer score, Timestamp date, User user) {
        this.score = score;
        this.date = date;
        this.user = user;
    }

    public Rank(Integer id, Integer score, Timestamp date, User user) {
        this.id = id;
        this.score = score;
        this.date = date;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String formatDate() {
        return Config.simpleDateFormat.format(date);
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(Integer userId) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", score=" + score +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
