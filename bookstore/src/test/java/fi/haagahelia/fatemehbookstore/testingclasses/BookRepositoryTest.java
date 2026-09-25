package fi.haagahelia.fatemehbookstore.testingclasses;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.haagahelia.fatemehbookstore.model.Book;
import fi.haagahelia.fatemehbookstore.model.BookRepository;
import fi.haagahelia.fatemehbookstore.model.Category;
import fi.haagahelia.fatemehbookstore.model.CategoryRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createNewBook() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        Book book = new Book("Test Book", "Test Author", 2024, "111", 10.0);
        book.setCategory(category);

        bookRepository.save(book);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        Book book = new Book("Delete Me", "Author", 2020, "222", 5.0);
        book.setCategory(category);

        bookRepository.save(book);

        Long id = book.getId();
        bookRepository.deleteById(id);

        assertThat(bookRepository.findById(id)).isEmpty();
    }

    @Test
    public void findByAuthor() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        Book book = new Book("Find Me", "Special Author", 2020, "333", 5.0);
        book.setCategory(category);

        bookRepository.save(book);

        List<Book> books = (List<Book>) bookRepository.findAll();

        assertThat(books).extracting(Book::getAuthor)
                         .contains("Special Author");
    }
}