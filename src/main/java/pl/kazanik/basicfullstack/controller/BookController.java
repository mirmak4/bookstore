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
import pl.kazanik.basicfullstack.response.BooksResponse;
import pl.kazanik.basicfullstack.service.BookService;

/**
 *
 * @author miron.maksymiuk
 */
@RestController
@RequestMapping(path = "api/v1")
// cross origin for localhost
public class BookController {
    
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping(path = "books")
    public ResponseEntity<BooksResponse> getAllBooks() {
        List<BookDto> books = bookService.fetchAllBooks();
        BooksResponse res = new BooksResponse();
        res.setBooks(books);
        return ResponseEntity.ok(res);
    }
}
