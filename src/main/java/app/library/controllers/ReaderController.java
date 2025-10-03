package app.library.controllers;

import app.library.dao.ReaderDAO;
import app.library.models.Reader;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class ReaderController {
    private final ReaderDAO readerDAO;

    @Autowired
    public ReaderController(ReaderDAO readerDAO) {
        this.readerDAO = readerDAO;
    }

    @GetMapping
    public String getAllReaders(Model model) {
        model.addAttribute("readers", readerDAO.getAllReaders());
        return "people/getAllReaders";
    }

    @GetMapping("/{id}")
    public String getReader(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", readerDAO.getReader(id));
        model.addAttribute("books", readerDAO.getBooksByReaderId(id));
        return "people/getReader";
    }

    @GetMapping("/new")
    public String newReader(@ModelAttribute("reader") Reader reader) {
        return "people/newReader";
    }

    @PostMapping
    public String createPerson(@ModelAttribute("reader") @Valid Reader reader, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "people/newReader";
        }

        readerDAO.createReader(reader);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editReader(@PathVariable("id") int id, Model model) {
        model.addAttribute("reader", readerDAO.getReader(id));
        return "people/editReader";
    }

    @PatchMapping("/{id}")
    public String updateReader(@ModelAttribute("reader") @Valid Reader reader,
                               BindingResult bindingResult, @PathVariable int id) {
        if(bindingResult.hasErrors()) {
            return "people/editReader";
        }

        readerDAO.updateReader(reader, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable("id") int id) {
        readerDAO.deleteReader(id);
        return "redirect:/people";
    }
}
