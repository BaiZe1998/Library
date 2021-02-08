package com.yltfy.service;

import com.yltfy.entity.Book;
import com.yltfy.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public int getPages();
    public void addBorrow(Integer bookid, Integer readerid);
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page);
    public int getBorrowPages(Integer id);
    public List<Borrow> findAllBorrowByState(Integer state, Integer page);
    public int getBorrowStatePages(Integer state);
    public void handle(Integer id, Integer adminId, Integer state);
}
