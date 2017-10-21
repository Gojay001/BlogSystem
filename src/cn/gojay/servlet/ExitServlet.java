package cn.gojay.servlet;

import cn.gojay.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 退出登陆
 * 移除session属性，返回登录界面
 */
public class ExitServlet extends HttpServlet {
    public ExitServlet() {
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
        //移除session属性
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if (admin != null) {
            session.removeAttribute("admin");
        }
        //返回登录界面
        request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
    }
}
