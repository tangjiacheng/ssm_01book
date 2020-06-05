package com.myssm.service;

import com.myssm.pojo.Book;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:15
 * @description: TODO
 */
public interface BookService {

    // 查询所有书籍
    List<Book> findAllBook();

    // 根据书名模糊查询
    List<Book> findBookByName(String bookName);

    // 根据ID查询
    Book findBookById(int bookId);

    // 添加书籍
    int addBook(Book book);

    // 移除书籍
    int deleteBook(int bookId);

    // 更新书籍信息
    int updateBook(Book book);
}
