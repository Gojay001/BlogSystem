package cn.gojay.servlet;

import cn.gojay.DB.BlogDB;
import cn.gojay.model.Blog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除博客
 * 通过id删除数据库中博客信息
 */
public class DeleteServlet extends HttpServlet {
    public DeleteServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取id，在数据库中查询博客并删除该博客
        int id = Integer.parseInt(request.getParameter("id"));
        Blog blog = BlogDB.searchBlogById(id);
        if (blog != null) {
            BlogDB.deleteBlog(id);
        }
        //返回首页
        request.getRequestDispatcher("Search?curPage=1").forward(request, response);
    }
}
