package com.yltfy.service.impl;

import com.yltfy.entity.Book;
import com.yltfy.entity.Borrow;
import com.yltfy.repository.BookRepository;
import com.yltfy.repository.BorrowRepository;
import com.yltfy.repository.impl.BookRepositoryImpl;
import com.yltfy.repository.impl.BorrowRepositoryImpl;
import com.yltfy.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class BookServiceImpl implements BookService {
    //类似的这种都是用接口的多态接受一个具体的实现类
    private BookRepository bookRepository = new BookRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    private final int LIMIT = 6;

    @Override
    public List<Book> findAll(int page) {
        return bookRepository.findAll(page, LIMIT);
    }

    //Service部分对数据库查到的数据再作一些简单的格式化操作，但原则上sql语句应完成绝大部分sql需要完成的查询
    //Service只是完成sql无法做到的与业务逻辑有关的二次加工
    @Override
    public int getPages() {
        int pages = bookRepository.getPages();
        if (pages % LIMIT == 0) return pages / LIMIT;
        else return pages / LIMIT + 1;
    }

    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        //这里service获得bookid 读者id 再获取当前时间 计算还书时间
        //借书时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = simpleDateFormat.format(date);
        //还书时间
        Calendar calendar = Calendar.getInstance();
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;
        calendar.set(Calendar.DAY_OF_YEAR, dates);
        Date date1 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date1);
        borrowRepository.insert(bookid, readerid, borrowTime, returnTime);
    }

    @Override
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page) {
        return borrowRepository.findAllBorrowByReaderId(id, page, LIMIT);
    }

    @Override
    public int getBorrowPages(Integer id) {
        int pages = borrowRepository.getBorrowPages(id);
        if (pages % LIMIT == 0) return pages / LIMIT;
        else return pages / LIMIT + 1;
    }
}
