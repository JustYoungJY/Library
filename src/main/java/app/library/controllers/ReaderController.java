package app.library.controllers;

import app.library.models.Reader;
import app.library.services.BookService;
import app.library.services.ReaderService;
import app.library.util.ReaderValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class ReaderController {
    private final ReaderService readerService;
    private final BookService bookService;
    private final ReaderValidator readerValidator;

    @Autowired
    public ReaderController(ReaderService readerService, BookService bookService, ReaderValidator readerValidator) {
        this.readerService = readerService;
        this.bookService = bookService;
        this.readerValidator = readerValidator;
    }

    @GetMapping
    public String getAllReaders(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "people/getAllReaders";
    }

    @GetMapping("/{id}")
    public String getReader(@PathVariable("id") Long id, Model model) {
        model.addAttribute("reader", readerService.getReader(id).orElse(null));
        model.addAttribute("books", bookService.getBooksByReaderId(id));
        return "people/getReader";
    }

    @GetMapping("/new")
    public String newReader(@ModelAttribute("reader") Reader reader) {
        return "people/newReader";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/newReader";
        }

        readerService.createReader(reader);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editReader(@PathVariable("id") Long id, Model model) {
        model.addAttribute("reader", readerService.getReader(id).orElse(null));
        return "people/editReader";
    }

    @PatchMapping("/{id}")
    public String updateReader(@ModelAttribute("reader") @Valid Reader reader,
                               BindingResult bindingResult, @PathVariable Long id) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/editReader";
        }

        readerService.updateReader(reader, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable("id") Long id) {
        readerService.deleteReader(id);
        return "redirect:/people";
    }
}
