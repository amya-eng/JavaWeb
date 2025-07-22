package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import sun.security.ssl.CookieExtension;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = service.login(username, password);

        String remember = request.getParameter("remember");

        if(user != null) {

            // 将user对象存储进session, 然后能在页面/selectAllServlet用EL表达式
            HttpSession session = request.getSession();
            session.setAttribute("user", user);


            // debug
            //System.out.println(contextPath);

            // 判断是否勾选了“记住我”复选框
            if("1".equals(remember)) {
                // 发送Cookie

                // 1. 创建Cookie对象
                Cookie  c_username = new Cookie("username", username);
                Cookie  c_password = new Cookie("password", password);

                c_username.setMaxAge(60*60*24*7);    // 7天
                c_password.setMaxAge(60*60*24*7);

                // 2. 发送到浏览器
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //登录成功，跳转到查询所有页面
            String contextPath = request.getContextPath();

            response.sendRedirect(contextPath+ "/selectAllServlet");
            //重定向，两次请求，需要session维持会话
        } else {
            // 登录失败
            // 存储错误信息到request
            request.setAttribute("login_msg", "用户名或密码错误");

            request.getRequestDispatcher("login.jsp").forward(request, response);


        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
