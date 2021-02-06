package com.yltfy.repository;

import com.yltfy.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
