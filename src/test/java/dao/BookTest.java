package dao;

import com.myssm.dao.BookMapper;
import com.myssm.pojo.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/5 14:48
 * @description: TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void TestFindAll() {
        List<Book> books = bookMapper.findAllBook();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void TestFindByName() {
        List<Book> books = bookMapper.findBookByName("%j%");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void TestFindById() {
        Book book = bookMapper.findBookById(1);
        System.out.println(book);
    }

    @Test
    public void TestAddBook() {
        Book book = new Book();
        book.setBookName("spring");
        book.setBookCount(10);
        book.setDetail("spring框架实战");

        bookMapper.addBook(book);
    }

    @Test
    public void TestDeleteBook() {
        bookMapper.deleteBook(4);
    }

    @Test
    public void TestUpdateBook() {
        Book book = bookMapper.findBookById(1);
        book.setBookCount(100);
        bookMapper.updateBook(book);
    }
}
