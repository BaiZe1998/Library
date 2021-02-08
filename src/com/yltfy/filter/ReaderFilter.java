package com.yltfy.filter;

import com.yltfy.entity.Reader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/book")
public class ReaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null)
            ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
        else
            filterChain.doFilter(servletRequest, servletResponse);
    }
}
