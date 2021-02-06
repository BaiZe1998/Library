package com.yltfy.service.impl;

import com.yltfy.entity.Book;
import com.yltfy.repository.BookRepository;
import com.yltfy.repository.impl.BookRepositoryImpl;
import com.yltfy.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    //类似的这种都是用接口的多态接受一个具体的实现类
    private BookRepository bookRepository = new BookRepositoryImpl();
    private final int LIMIT = 6;

    @Override
    public List<Book> findAll(int page) {
        return bookRepository.findAll(page, LIMIT);
    }

    //Service部分对数据库查到的数据再作一些简单的格式化操作，但原则上sql语句应完成绝大部分sql需要完成的查询
    //Service只是完成sql无法做到的与业务逻辑有关的二次加工
    @Override
    public int getPages() {
        int pages = bookRepository.getPages();
        if (pages % LIMIT == 0) return pages / LIMIT;
        else return pages / LIMIT + 1;
    }
}
