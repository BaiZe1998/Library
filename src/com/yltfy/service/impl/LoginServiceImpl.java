package com.yltfy.service.impl;

import com.yltfy.entity.Reader;
import com.yltfy.repository.ReaderRepository;
import com.yltfy.repository.impl.ReaderRepositoryImpl;
import com.yltfy.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();

    @Override
    public Reader login(String username, String password) {
        return readerRepository.login(username, password);
    }
}
