package com.myssm.service.impl;

import com.myssm.dao.BookMapper;
import com.myssm.pojo.Book;
import com.myssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:31
 * @description: TODO
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAllBook() {
        return bookMapper.findAllBook();
    }

    @Override
    public List<Book> findBookByName(String bookName) {
        return bookMapper.findBookByName(bookName);
    }

    @Override
    public Book findBookById(int bookId) {
        return bookMapper.findBookById(bookId);
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBook(int bookId) {
        return bookMapper.deleteBook(bookId);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }
}
