package cn.gojay.servlet;

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
 * 预览博客
 * 获取博客信息并显示
 * 设置blogBean实体属性
 */
public class PreviewServlet extends HttpServlet {
    public PreviewServlet() {
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
        //获取文章信息
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int day = Integer.parseInt(request.getParameter("day"));
        Date date = MyDate.getSqlDate(year, month, day);
        String title = request.getParameter("title");
        String blogText = request.getParameter("blogText");
        HttpSession session = request.getSession();
        Admin adminBean = (Admin)session.getAttribute("adminBean");
        String username = adminBean.getName();
        Blog blogBean;

        //是否为修改后上传
        if (session.getAttribute("update") != null) {
            int id = (int)session.getAttribute("id");
            blogBean = new Blog(id, blogText, username, date, title);
        } else {
            blogBean = new Blog(blogText, username, date, title);
        }

        //设置请求属性，返回文章内容
        session.setAttribute("blogBean", blogBean);
        request.setAttribute("submit", true);
        request.getRequestDispatcher("jsp/preview.jsp").forward(request, response);
    }
}
