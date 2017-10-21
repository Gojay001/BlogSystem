package cn.gojay.servlet;

import cn.gojay.DB.BlogDB;
import cn.gojay.model.Blog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

/**
 * 更新博客
 * 获取需要修改的博客id并将信息转发给publish.jsp
 */
public class EditServlet extends HttpServlet {
    public EditServlet() {
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
        //通过id查询需要更新的博客
        int id = Integer.parseInt(request.getParameter("id"));
        Blog blogBean = BlogDB.searchBlogById(id);
        //获取时间
        Date date = blogBean.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if (Integer.parseInt(month) < 10) {
            month = 0 + month;
        }
        int day = calendar.get(Calendar.DATE);

        //将查询到的博客信息添加到请求属性
        request.setAttribute("title", blogBean.getBlogTitle());
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("day", day);
        request.setAttribute("blogText", blogBean.getBlogText());

        HttpSession session = request.getSession(true);
        session.setAttribute("update", "true");
        session.setAttribute("id", id);
        request.getRequestDispatcher("jsp/publish.jsp").forward(request, response);
    }
}
