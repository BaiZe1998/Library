package com.yltfy.controller;

import com.yltfy.entity.Admin;
import com.yltfy.entity.Borrow;
import com.yltfy.service.BookService;
import com.yltfy.service.impl.BookServiceImpl;
import jdk.jshell.spi.SPIResolutionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null)
            method = "findAll";
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        switch (method) {
            case "findAll":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                //查询borrow表中state为0的所有数据 分页处理 获得的数据是已经经过分页的
                List<Borrow> list = bookService.findAllBorrowByState(0, page);
                req.setAttribute("list", list);
                req.setAttribute("dataPrePage", 6);
                req.setAttribute("currentPage", page);
                //查询有多少页，结果已经是在service中完成转换的页数
                req.setAttribute("pages", bookService.getBorrowStatePages(0));
                req.getRequestDispatcher("/admin.jsp").forward(req, resp);
                break;
            case "handle":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                String idStr = req.getParameter("borrowId");
                Integer id = Integer.parseInt(idStr);
                String stateStr = req.getParameter("state");
                Integer state = Integer.parseInt(stateStr);
                bookService.handle(id, admin.getId(), state);
                if (state <= 2)
                    resp.sendRedirect("/admin?page=" + page);
                else
                    resp.sendRedirect("/admin?method=getBorrowed&page=" + page);
                break;
            case "getBorrowed":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                //查询所有状态为1的借书数据，并且分页处理
                list = bookService.findAllBorrowByState(1, page);
                req.setAttribute("list", list);
                req.setAttribute("dataPrePage", 6);
                req.setAttribute("currentPage", page);
                //查询有多少页，结果已经是在service中完成转换的页数
                req.setAttribute("pages", bookService.getBorrowStatePages(1));
                req.getRequestDispatcher("/return.jsp").forward(req, resp);
                break;
        }
    }
}
