package cn.gojay.servlet;

import cn.gojay.DB.BlogDB;
import cn.gojay.model.Admin;
import cn.gojay.model.Blog;
import cn.gojay.encode.MyDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

/**
 * 添加博客
 * 1、从publish.jsp直接提交新博客
 * 2、从preview提交新博客
 * 3、从publish.jsp提交修改后博客
 * 4、从preview提交修改后博客
 * 写入数据库成功则返回首页
 */
public class AddServlet extends HttpServlet {
    public AddServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String blogText;
        String userName;
        Date date;
        String title;
        boolean status;
        Blog blogBean;
        HttpSession session = request.getSession(false);

        //从preview转发请求
        if (session.getAttribute("blogBean") != null) {
            blogBean = (Blog)session.getAttribute("blogBean");

            if (session.getAttribute("update") != null) {
                //修改后更新博客
                status = BlogDB.updateBlog(blogBean);
                session.removeAttribute("update");
                session.removeAttribute("id");
            } else {
                //新增博客
                status = BlogDB.AddBlog(blogBean);
            }
            //移除session中blogBean属性
            session.removeAttribute("blogBean");
        } else {
            //从publish.jsp转发请求

            //获取页面内容
            blogText = request.getParameter("blogText");
            Admin admin = (Admin)request.getSession().getAttribute("adminBean");
            userName = admin.getName();
            title = request.getParameter("title");
            //得到日期
            int year = Integer.parseInt(request.getParameter("year"));
            int month = Integer.parseInt(request.getParameter("month"));
            int day = Integer.parseInt(request.getParameter("day"));
            date = MyDate.getSqlDate(year, month, day);
            //判断是否为修改后的博客
            String update = (String)session.getAttribute("update");
            if (update != null) {
                //获取博客id，修改数据库中博客信息
                int id = (int)session.getAttribute("id");
                blogBean = new Blog(id, blogText, userName, date, title);
                status = BlogDB.updateBlog(blogBean);
                session.removeAttribute("update");
                session.removeAttribute("id");
            } else {
                //新增博客，直接写入数据库
                blogBean = new Blog(blogText, userName, date, title);
                status = BlogDB.AddBlog(blogBean);
            }
        }

        if (status) {
            //写入成功，返回首页
            request.getRequestDispatcher("Search?curPage=1").forward(request, response);
        } else {
            //写入失败，返回发布页面
            request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
        }
    }
}
