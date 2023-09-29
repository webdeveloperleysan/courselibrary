package com.application.courselibrary.controller;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/authors")
    public String findAllAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    @GetMapping("/remove-author/{id}")
    public String deleteAuthorById(@PathVariable Long id, Model model) {
        authorService.deleteAuthorById(id);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    @GetMapping("/update-author/{id}")
    public String showUpdateAuthor(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findAuthorById(id));
        return "update-author";
    }

    @PostMapping("/update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-author";
        }
        authorService.updateAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    @GetMapping("/add-author")
    public String showAddAuthor(Author author) {
        return "add-author";
    }

    @PostMapping("save-author")
    public String addAuthor(Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-author";
        }
        authorService.createNewAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }

}
