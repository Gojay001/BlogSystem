package cn.gojay.servlet;

import cn.gojay.DB.BlogDB;
import cn.gojay.model.Blog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 查询博客
 * 通过标题查询博客实体列表
 */
public class SearchServlet extends HttpServlet {
    //每页显示数据数量
    private static final int dataPerPage = 10;

    public SearchServlet() {
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
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        String title;
        HttpSession session = request.getSession();

        //搜索框有新内容则更新title
        if (request.getParameter("title") != null) {
            title = request.getParameter("title");
            session.setAttribute("title", title);
        } else {
            title = (String)session.getAttribute("title");
        }

        //获取查询到的博客列表和总页数
        ArrayList<Blog> blogs = new ArrayList<>();
        if (title != null) {
            blogs = BlogDB.searchBlogByTitle(title, curPage, dataPerPage);
        }
        int totalPage = BlogDB.getTotalPage(title, dataPerPage);

        //返回列表信息
        request.setAttribute("blogs", blogs);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("jsp/index.jsp").forward(request, response);

    }
}
