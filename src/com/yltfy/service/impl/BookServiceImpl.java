package com.yltfy.service.impl;

import com.yltfy.entity.Book;
import com.yltfy.repository.BookRepository;
import com.yltfy.repository.impl.BookRepositoryImpl;
import com.yltfy.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    //类似的这种都是用接口的多态接受一个具体的实现类
    private BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
