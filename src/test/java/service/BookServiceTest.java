package service;

import com.myssm.pojo.Book;
import com.myssm.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:30
 * @description: TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void testFindAll() {
        List<Book> books = bookService.findAllBook();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void TestFindByName() {
        List<Book> books = bookService.findBookByName("%j%");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void TestFindById() {
        Book book = bookService.findBookById(1);
        System.out.println(book);
    }

    @Test
    public void TestAddBook() {
        Book book = new Book();
        book.setBookName("spring");
        book.setBookCount(10);
        book.setDetail("spring框架实战");

        bookService.addBook(book);
    }

    @Test
    public void TestDeleteBook() {
        bookService.deleteBook(4);
    }

    @Test
    public void TestUpdateBook() {
        Book book = bookService.findBookById(5);
        book.setBookCount(100);
        bookService.updateBook(book);
    }
}
