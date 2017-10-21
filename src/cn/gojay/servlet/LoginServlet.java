package cn.gojay.servlet;

import cn.gojay.DB.UserDB;
import cn.gojay.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证用户登陆
 * 验证用户登陆信息是否正确
 */
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
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
        //从HTTP请求获取用户信息
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        //查询用户是否存在
        boolean status = UserDB.login(name, password);
        if (status == true) {
            Admin admin = new Admin(name, password);
            success(request, response, admin);
        } else {
            String message = "用户名或密码错误！";
            fail(request, response, message);
        }

    }

    /**
     * 成功登陆，储存用户信息
     */
    private void success(HttpServletRequest request, HttpServletResponse response, Admin admin)
            throws ServletException, IOException {
        //保存用户登录信息
        HttpSession session = request.getSession(true);
        if (session.getAttribute("adminBean") != null) {
            session.removeAttribute("adminBean");
        }
        session.setAttribute("adminBean", admin);
        session.setMaxInactiveInterval(6000);
        //将请求转发
        try {
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        } catch (Exception e) {
            String message = "登陆失败！";
            fail(request, response, message);
        }
    }

    /**
     * 登陆失败
     */
    private void fail(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print(message + "<br>");
        out.print("返回"+"<a href=/>登陆界面</a>");
    }

}
