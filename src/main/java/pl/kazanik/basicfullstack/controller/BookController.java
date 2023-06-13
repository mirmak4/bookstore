/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kazanik.basicfullstack.dto.BookDto;

/**
 *
 * @author miron.maksymiuk
 */
@RestController
@RequestMapping(path = "api/v1")
// cross origin for localhost
public class BookController {
    
    @GetMapping(path = "books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        BookDto book = BookDto.builder()
                .title("Lord of the Rings")
                .build();
        BookDto book2 = BookDto.builder()
                .title("Dune")
                .build();
        BookDto book3 = BookDto.builder()
                .title("Hobbit")
                .build();
        List<BookDto> books = List.of(book, book2, book3);
        return ResponseEntity.ok(books);
    }
}
