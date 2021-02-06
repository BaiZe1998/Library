package com.yltfy.repository;

import com.yltfy.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username, String password);
}
