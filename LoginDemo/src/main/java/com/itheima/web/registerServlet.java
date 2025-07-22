package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")            // 如果缺少“/”将会导致tomcat启动失败
public class registerServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String checkCode = request.getParameter("checkCode");

        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCode");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if(!checkCodeGen.equals(checkCode)){
            request.setAttribute("valid_msg", "验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            // 不允许注册
            return;
        }

        boolean success = service.register(user);
        if(success) {
            // 注册成功，跳转登录页面
            request.setAttribute("register_msg", "注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("register_msg", "用户名重复");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
