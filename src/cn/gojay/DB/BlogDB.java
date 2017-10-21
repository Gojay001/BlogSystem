package cn.gojay.DB;

import cn.gojay.model.Blog;

import java.sql.*;
import java.util.ArrayList;

/**
 * 博客操作类
 * 博客文章与数据库相关的CRUD操作
 */
public class BlogDB {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    /**
     * 增加博客
     */
    public static boolean AddBlog(Blog blogBean) {
        boolean bool = false;
        conn = ConnectionDB.getConnection();
        String SQL = "INSERT INTO BLOG(blogText,name,date,blogTitle) VALUES(?,?,?,?)";

        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, blogBean.getBlogText());
            ps.setString(2, blogBean.getName());
            ps.setDate(3, blogBean.getDate());
            ps.setString(4, blogBean.getBlogTitle());
            int rs = ps.executeUpdate();
            if (rs != -1) {
                bool = true;
            }
        }catch (SQLException e) {
            System.out.println("写入数据库失败！");
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库失败！");
            }
            ConnectionDB.closeConnection(conn);
        }
        return bool;
    }

    /**
     * 通过title模糊查询所有博客
     * @param title 博客标题
     * @param curPage 首页当前页
     * @param dataPerPage 每页博客数目
     * @return 所有符合该标题博客实体
     */
    public static ArrayList<Blog> searchBlogByTitle(String title, int curPage, int dataPerPage) {
        ArrayList<Blog> blogs = new ArrayList<>();
        Blog blog = null;

        conn = ConnectionDB.getConnection();
        String SQL = "SELECT * FROM BLOG WHERE blogTitle LIKE ? ORDER BY blogId ASC LIMIT ?,?";
        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, (curPage - 1) * dataPerPage);
            ps.setInt(3, dataPerPage);

            rs = ps.executeQuery();
            while (rs.next()) {
                int blogId = rs.getInt(1);
                String blogText = rs.getString(2);
                String name = rs.getString(3);
                Date date = rs.getDate(4);
                String blogTitle = rs.getString(5);

                blog = new Blog(blogId, blogText, name, date, blogTitle);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            System.out.println("读取数据库失败！");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库失败！");
            }
            ConnectionDB.closeConnection(conn);
        }
        return blogs;
    }

    /**
     * 获取博客所有页数
     * @param title 博客标题
     * @param dataPerPage 每页博客数目
     * @return 博客所有页数
     */
    public static int getTotalPage(String title, int dataPerPage) {
        int count = 0;
        conn = ConnectionDB.getConnection();
        String SQL = "SELECT COUNT(*) FROM BLOG WHERE blogTitle LIKE ?";

        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            count = (int)Math.ceil((count + 1.0 - 1.0)/dataPerPage);
        } catch (SQLException e) {
            System.out.println("读取数据库失败！");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库失败！");
            }
            ConnectionDB.closeConnection(conn);
        }

        return count;
    }

    /**
     * 通过id查询博客实体
     * @param id 博客id
     * @return 博客实体
     */
    public static Blog searchBlogById(int id) {
        Blog blog = null;

        conn = ConnectionDB.getConnection();
        String SQL = "SELECT * FROM BLOG WHERE blogId=?";
        try {
            ps = conn.prepareStatement(SQL);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                int blogId = rs.getInt(1);
                String blogText = rs.getString(2);
                String name = rs.getString(3);
                Date date = rs.getDate(4);
                String blogTitle = rs.getString(5);

                blog = new Blog(blogId, blogText, name, date, blogTitle);
            }
        } catch (SQLException e) {
            System.out.println("读取数据库失败！");
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库失败！");
            }
            ConnectionDB.closeConnection(conn);
        }

        return blog;
    }

    /**
     * 删除博客
     * @param id 博客id
     * @return boolean标志
     */
    public static boolean deleteBlog(int id) {
        boolean bool = false;

        conn = ConnectionDB.getConnection();
        String SQL = "DELETE FROM BLOG WHERE blogId=?";
        try {
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,id);
            int rs = ps.executeUpdate();
            if (rs != -1) {
                bool = true;
            }
        } catch (SQLException e) {
            System.out.println("访问数据库失败！");
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库失败！");
            }
            ConnectionDB.closeConnection(conn);
        }

        return bool;
    }

    /**
     * 修改博客
      * @param blogBean 修改后博客实体
     * @return boolean标志
     */
    public static boolean updateBlog(Blog blogBean) {
        boolean bool = false;
        conn = ConnectionDB.getConnection();
        String SQL = "UPDATE BLOG SET blogText=?, name=?, date=?, blogTitle=? WHERE blogId=?";

        try {
            ps = conn.prepareStatement(SQL);
            ps.setString(1, blogBean.getBlogText());
            ps.setString(2, blogBean.getName());
            ps.setDate(3, blogBean.getDate());
            ps.setString(4, blogBean.getBlogTitle());
            ps.setInt(5, blogBean.getBlogId());
            int rs = ps.executeUpdate();
            if (rs != -1) {
                bool = true;
            }
        }catch (SQLException e) {
            System.out.println("写入数据库失败！");
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("关闭数据库失败！");
            }
            ConnectionDB.closeConnection(conn);
        }
        return bool;
    }
}
