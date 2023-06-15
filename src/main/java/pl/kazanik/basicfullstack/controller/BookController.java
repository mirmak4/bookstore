/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kazanik.basicfullstack.entity.Book;
import pl.kazanik.basicfullstack.repository.BookRepository;

/**
 *
 * @author miron.maksymiuk
 */
@RestController
@RequestMapping(path = "api/v1")
// cross origin for localhost
public class BookController {
    
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @GetMapping(path = "books")
    public ResponseEntity<List<Book>> getAllBooks() {
        Book book = Book.builder()
                .title("Lord of the Rings")
                .author("J.R.R. Tolkien")
                .releaseYear(1951)
                .description("Trilogy")
                .build();
        Book book2 = Book.builder()
                .title("Dune")
                .author("Frank Herbert")
                .releaseYear(1951)
                .description("Part I")
                .build();
        Book book3 = Book.builder()
                .title("Hobbit")
                .author("J.R.R. Tolkien")
                .releaseYear(1951)
                .description("Story")
                .build();
        List<Book> books = List.of(book, book2, book3);
//        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }
}
