package com.yltfy.controller;

import com.yltfy.entity.Reader;
import com.yltfy.service.LoginService;
import com.yltfy.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆业务的控制器
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Reader reader = loginService.login(username, password);
        if (reader != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", reader);
        } else {
            resp.sendRedirect("/login.jsp");
        }
    }
}
