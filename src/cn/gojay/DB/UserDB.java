package cn.gojay.DB;

import java.sql.*;

/**
 * 用户登陆类
 * 查询用户登陆信息
 */
public class UserDB {
    /**
     * 查询登陆信息是否存在
     * @param name 用户名
     * @param password 密码
     * @return bool boolean标志
     */
    public static boolean login(String name, String password) {
        boolean bool = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = ConnectionDB.getConnection();
        String SQL = "SELECT * FROM ADMIN WHERE name=? AND password=?";

        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                bool = true;
            }
        }catch (SQLException e) {
            System.out.println("连接数据库出错！");
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库失败！");
            }
            ConnectionDB.closeConnection(conn);
        }

        return bool;
    }
}
