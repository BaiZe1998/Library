package com.yltfy.repository.impl;

import com.yltfy.entity.Book;
import com.yltfy.entity.Borrow;
import com.yltfy.entity.Reader;
import com.yltfy.repository.BorrowRepository;
import com.yltfy.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select borrow.id, book.name, book.author, book.publish, borrow.borrowtime, borrow.returntime, reader.name, reader.tel, reader.cardid, borrow.state from borrow, book, reader where borrow.readerid = ? and book.id = borrow.bookid and reader.id = borrow.readerid limit ?, ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, (page -1 ) * limit);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            //循环往list中插入Borrow对象
            while (resultSet.next()) {
//                int borrowId = resultSet.getInt("1");
//                String bookName = resultSet.getString("2");
//                String author = resultSet.getString("3");
//                String publish = resultSet.getString("4");
//                String borrowTime = resultSet.getString("5");
//                String returnTime = resultSet.getString("6");
//                String readerName = resultSet.getString("7");
//                String tel = resultSet.getString("8");
//                String cardId = resultSet.getString("9");
//                Integer state = resultSet.getInt("10");
                //封装
//                Book book = new Book(resultSet.getString("2"), resultSet.getString("3"), resultSet.getString("4"));
//                Reader reader = new Reader(resultSet.getString("5"), resultSet.getString("6"), resultSet.getString("7"));
                Borrow borrow =
                        new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)),
                        new Reader(resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)),
                                resultSet.getString(5), resultSet.getString(6), resultSet.getInt(10));
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public int getBorrowPages(Integer id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow, book, reader where borrow.readerid = ? and book.id = borrow.bookid and reader.id = borrow.readerid";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<Borrow> findAllBorrowByState(Integer state, Integer page, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select borrow.id, book.name, book.author, book.publish, borrow.borrowtime, borrow.returntime, reader.name, reader.tel, reader.cardid, borrow.state from borrow, book, reader where borrow.state = ? and book.id = borrow.bookid and reader.id = borrow.readerid limit ?, ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, state);
            preparedStatement.setInt(2, (page -1 ) * limit);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            //循环往list中插入Borrow对象
            while (resultSet.next()) {
                Borrow borrow =
                        new Borrow(resultSet.getInt(1),
                                new Book(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)),
                                new Reader(resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)),
                                resultSet.getString(5), resultSet.getString(6), resultSet.getInt(10));
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public int borrowRepository(Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow, book, reader where borrow.state = ? and book.id = borrow.bookid and reader.id = borrow.readerid";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, state);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public void handle(Integer id, Integer adminId, Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update borrow set state = ?, adminid = ? where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, state);
            preparedStatement.setInt(2, adminId);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }
}
