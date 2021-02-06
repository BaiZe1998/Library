package com.yltfy.controller;

import com.yltfy.entity.Book;
import com.yltfy.service.BookService;
import com.yltfy.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 图书展示等操作的控制器
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取数据
        String pageStr = req.getParameter("page");
        Integer page = Integer.parseInt(pageStr);
        //获取当前分页的数据 以及总页数
        List<Book> list = bookService.findAll(page);
        req.setAttribute("pages", bookService.getPages());
        req.setAttribute("list", list);
        req.setAttribute("dataPrePage", 6);
        req.setAttribute("currentPage", page);
        //将数据发送到视图
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
