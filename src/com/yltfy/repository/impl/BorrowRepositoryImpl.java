package com.yltfy.repository.impl;

import com.yltfy.repository.BorrowRepository;
import com.yltfy.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowRepositoryImpl implements BorrowRepository {
    //负责将数据插入borrow表
    @Override
    public void insert(Integer bookid, Integer readerid, String borrowTime, String returnTime) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid, readerid, borrowtime, returntime, state) values (?, ?, ?, ?, 0)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookid);
            preparedStatement.setInt(2, readerid);
            preparedStatement.setString(3, borrowTime);
            preparedStatement.setString(4, returnTime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }
}
