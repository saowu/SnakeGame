package com.wuyanbo.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 *
 * @author yhj
 */
public class DBUtil {
    private static String driver;//JDBC驱动类字符串
    private static String url;//链接数据库的路径
    private static String user;//用户名
    private static String password;//密码

    static {
        //使用类路径的读取方式
        InputStream inStream = DBUtil.class.getResourceAsStream("/db.properties");
        //创建用于加载输入流的Properties对象
        Properties properties = new Properties();
        try {
            //加载文件
            properties.load(inStream);
            //读取jdbc参数信息
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.user");
            password = properties.getProperty("jdbc.password");
            //注册驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//
//    private static String driver = "com.mysql.jdbc.Driver";
//    private static String url = "jdbc:mysql://localhost:3306/snake?useUnicode=true&characterEncoding=utf8";
//    private static String user = "root";
//    private static String password = "123456";


    //获取连接的方法
    public static Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 方法描述：释放资源
     *
     * @param resultSet  结果集
     * @param statement  命令对象
     * @param connection 连接对象
     */
    public static void releaseDB(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
