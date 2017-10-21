package cn.gojay.DB;

import java.sql.*;

/**
 * 数据库连接类
 * 建立与数据库的连接与关闭
 */
public class ConnectionDB {
    /**
     * 获取数据连接
     * @return conn Connection对象
     */
    public static Connection getConnection() {
        Connection conn = null;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/BLOGSYSTEM";
        String username = "root";
        String password = "root";

        try {
            //注册驱动
            Class.forName(JDBC_DRIVER);
            //获取数据库连接
            conn = DriverManager.getConnection(DB_URL, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败！");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("获取数据库连接失败！");
        }

        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn 需要关闭的Connection对象
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("数据库关闭异常！");
            }
        }
    }
}
