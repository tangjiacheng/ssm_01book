package com.myssm.controller;

import com.myssm.pojo.Book;
import com.myssm.pojo.User;
import com.myssm.service.BookService;
import com.myssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: TJC
 * @Date: 2020/6/5 15:49
 * @description: TODO
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(HttpSession session) {
        if (session.getAttribute("userLoginInfo") != null) {
            return "redirect:/book/main";
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, String username, String password, Model model) {
        System.out.println("login ==> " + username + ", " + password);
        User user = userService.findUserByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }else {
            session.setAttribute("userLoginInfo", username);
            return "redirect:/book/main";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        // 移除session中的用户信息
        session.removeAttribute("userLoginInfo");
        return "login";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        List<Book> books = bookService.findAllBook();
        model.addAttribute("books", books);
        return "main";
    }

    //跳转到添加书籍页面
    @RequestMapping("/toAdd")
    public String toAddPage() {
        return "addBook";
    }

    //添加书籍
    @RequestMapping("/addBook")
    public String addBook(Book book) {
        bookService.addBook(book);
        return "redirect:/book/allBook"; // 重定向
    }

    //跳转到修改页面
    @RequestMapping("/toUpdate")
    public String toUpdatePage(int bookId, Model model) {
        Book book = bookService.findBookById(bookId);
        model.addAttribute("QBook", book);
//        System.out.println("toUpdate == > " + book);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/book/main";
    }

    //删除书籍
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/book/main";
    }

    //查询书籍
    @RequestMapping("/findBook")
    public String queryBook(String queryBookName, Model model) {
        List<Book> books = bookService.findBookByName("%" + queryBookName + "%");
        if (books.isEmpty()) {
            model.addAttribute("error", "未查询到 " + queryBookName);
        }
        model.addAttribute("books", books);
        return "main";
    }
}
