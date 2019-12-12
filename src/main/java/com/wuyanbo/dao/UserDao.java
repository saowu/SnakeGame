package com.wuyanbo.dao;

import com.wuyanbo.po.User;
import com.wuyanbo.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description:
 * @author: wuyanbo
 * @create: 2019-12-03 14:05
 */

public class UserDao {

    /**
     * 登录验证
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        Connection connection = DBUtil.getConnection();
        //区分大小写
        //1.加 binary关键字，强制它后面的字符串为一个二进制字符串
        //2.Mysql默认的字符检索策略：utf8_general_ci，表示不区分大小写；utf8_general_cs表示区分大小写，utf8_bin表示二进制比较，同样也区分大小写
        String sql = "select * from users where binary username = ? and binary password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
        return null;
    }

    /**
     * 更新指定id用户名
     *
     * @param id
     * @param name
     * @return
     */
    public boolean updateUserName(Integer id, String name) {
        Connection connection = DBUtil.getConnection();
        String sql = "update users set username = ? where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
        return false;
    }

    /**
     * 更新指定id 密码
     *
     * @param id
     * @param passwd
     * @return
     */
    public boolean updateUserPassword(Integer id, String passwd) {
        Connection connection = DBUtil.getConnection();
        String sql = "update users set password = ? where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passwd);
            preparedStatement.setInt(2, id);
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
        return false;
    }

    /**
     * 查询全部
     */
    public void selectAll() {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from users";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "|" + resultSet.getString(2) + "|" + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public boolean deleteUser(Integer id) {
        Connection connection = DBUtil.getConnection();
        String sql = "delete users where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
        return false;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    public User registered(User user) {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into users (username,password) values (?,?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
        return null;
    }

    /**
     * 查询单个对象
     *
     * @param username
     * @return
     */
    public User selectUser(String username) {
        Connection connection = DBUtil.getConnection();
        String sql = "select * from users where username = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.releaseDB(resultSet, preparedStatement, connection);
        }
        return null;
    }

}
