package app.library.controllers;

import app.library.dao.BookDAO;
import app.library.dao.ReaderDAO;
import app.library.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final ReaderDAO readerDAO;

    public BookController(BookDAO bookDAO, ReaderDAO readerDAO) {
        this.bookDAO = bookDAO;
        this.readerDAO = readerDAO;
    }

    @GetMapping
    public String getAllBook(Model model) {
        model.addAttribute("books", bookDAO.getALlBooks());
        return "books/getAllBooks";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        model.addAttribute("reader", bookDAO.getBookReader(id));
        model.addAttribute("readers", readerDAO.getAllReaders());
        return "books/getBook";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("model") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/newBook";
        }

        bookDAO.createBook(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String getEditBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("model") Book book, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "books/editBook";
        }

        bookDAO.updateBook(book, id);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") int bookId, @RequestParam int readerId) {
        bookDAO.assignBook(bookId, readerId);
        return "redirect:/book/" + bookId;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int bookId) {
        bookDAO.releaseBook(bookId);
        return "redirect:/book/" + bookId;
    }
}
