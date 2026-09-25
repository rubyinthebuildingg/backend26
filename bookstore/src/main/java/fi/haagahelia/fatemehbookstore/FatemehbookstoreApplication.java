package fi.haagahelia.fatemehbookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.fatemehbookstore.model.Book;
import fi.haagahelia.fatemehbookstore.model.BookRepository;
import fi.haagahelia.fatemehbookstore.model.Category;
import fi.haagahelia.fatemehbookstore.model.CategoryRepository;

@SpringBootApplication
public class FatemehbookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FatemehbookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository bookRepo, CategoryRepository catRepo) {
        return (args) -> {

            Category fiction = new Category("Fiction");
            Category science = new Category("Science");

            catRepo.save(fiction);
            catRepo.save(science);

            Book b1 = new Book("Animal Farm", "Orwell", 1945, "123", 12.5);
            b1.setCategory(fiction);

            Book b2 = new Book("Cosmos", "Sagan", 1980, "456", 15.0);
            b2.setCategory(science);

            bookRepo.save(b1);
            bookRepo.save(b2);
        };
    }
}