package app.library.controllers;

import app.library.models.Book;
import app.library.services.BookService;
import app.library.services.ReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    private final ReaderService readerService;
    private final BookService bookService;

    @Autowired
    public BookController(ReaderService readerService, BookService bookService) {
        this.readerService = readerService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBook(Model model, @RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                             @RequestParam(value = "sort_by_year", defaultValue = "false") boolean sortByYear) {
        model.addAttribute("books", bookService.getAllBooks(page, booksPerPage, sortByYear));

        return "books/getAllBooks";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBook(id).orElse(null));
        model.addAttribute("reader", readerService.getReaderByBookId(id).orElse(null));
        model.addAttribute("readers", readerService.getAllReaders());
        return "books/getBook";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/newBook";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/newBook";
        }

        bookService.createBook(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String getEditBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBook(id).orElse(null));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                             @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "books/editBook";
        }

        book.setId(id);
        bookService.updateBook(book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") Long bookId, @RequestParam Long readerId) {
        bookService.assignBook(bookId, readerId);
        return "redirect:/book/" + bookId;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") Long bookId) {
        bookService.releaseBook(bookId);
        return "redirect:/book/" + bookId;
    }

    @GetMapping("/search")
    public String searchBook(Model model, @RequestParam(value = "pattern", required = false) String pattern) {
        model.addAttribute("book", bookService.getBookByPattern(pattern).orElse(null));
        return "books/searchBook";
    }
}
