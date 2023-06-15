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
import pl.kazanik.basicfullstack.dto.BookDto;
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
    public ResponseEntity<List<BookDto>> getAllBooks() {
        BookDto book = BookDto.builder()
                .id(1l)
                .title("Lord of the Rings")
                .author("J.R.R. Tolkien")
                .releaseYear(1951)
                .description("Trilogy")
                .build();
        BookDto book2 = BookDto.builder()
                .id(2l)
                .title("Dune")
                .author("Frank Herbert")
                .releaseYear(1951)
                .description("Part I")
                .build();
        BookDto book3 = BookDto.builder()
                .id(3l)
                .title("Hobbit")
                .author("J.R.R. Tolkien")
                .releaseYear(1951)
                .description("Story")
                .build();
        List<BookDto> books = List.of(book, book2, book3);
//        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }
}
