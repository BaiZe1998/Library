package com.yltfy.repository;

import com.yltfy.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    public void insert(Integer bookid, Integer readerid, String borrowTime, String returnTime);
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page, Integer limit);
    public int getBorrowPages(Integer id);
    public List<Borrow> findAllBorrowByState(Integer state, Integer page, Integer limit);
    public int borrowRepository(Integer state);
    public void handle(Integer id, Integer adminId, Integer state);
}
