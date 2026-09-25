package fi.haagahelia.fatemehbookstore.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.fatemehbookstore.model.Book;
import fi.haagahelia.fatemehbookstore.model.BookRepository;

@RestController
public class BookRestController {

    private final BookRepository repository;

    public BookRestController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/mybooks")
    public Iterable<Book> getBooks() {
        return repository.findAll();
    }

    @GetMapping("/api/mybooks/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long id) {
        return repository.findById(id);
    }
}