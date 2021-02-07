package com.yltfy.repository;

public interface BorrowRepository {
    public void insert(Integer bookid, Integer readerid, String borrowTime, String returnTime);
}
