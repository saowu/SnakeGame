package com.wuyanbo.dao;

import com.wuyanbo.po.Rank;
import com.wuyanbo.po.User;
import com.wuyanbo.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wuyanbo
 * @create: 2019-12-06 16:45
 */

public class RankDao {
    /**
     * 查询成绩最高的5条
     *
     * @return
     */
    public List<Rank> selectRanks() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Rank> ranks = new ArrayList<>();

        String sql = "select r.score,r.date,u.username from users u,ranks r where u.id=r.userId order by r.score desc limit ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 5);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ranks.add(new Rank(resultSet.getInt(1), resultSet.getTimestamp(2), new User(resultSet.getString(3))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }

        return ranks;
    }

    /**
     * 插入记录
     *
     * @param rank
     * @return
     */
    public Rank insert(Rank rank) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "insert into ranks (score,date,userId) values (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, rank.getScore());
            preparedStatement.setTimestamp(2, rank.getDate());
            preparedStatement.setInt(3, rank.getUser().getId());
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                return rank;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
        return null;
    }
}
