package com.yltfy.service.impl;

import com.yltfy.entity.Reader;
import com.yltfy.repository.AdminRepository;
import com.yltfy.repository.ReaderRepository;
import com.yltfy.repository.impl.AdminRepositoryImpl;
import com.yltfy.repository.impl.ReaderRepositoryImpl;
import com.yltfy.service.LoginService;

/**
 * controller负责发送命令和接受数据 service包中的实现类负责根据命令处理业务逻辑 repository负责查询数据库
 */
public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();

    @Override
    public Object login(String username, String password, String type) {
        Object object = null;
        switch (type) {
            case "admin":
                object = adminRepository.login(username, password);
                break;
            case "reader":
                object = readerRepository.login(username, password);
                break;
        }
        return object;
    }
}
