/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Book Api", tags = "Book Api", produces = "application/json")
@RestController
@RequestMapping(path = "api/v1")
// cross origin for localhost
public class BookController {
    
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @ApiOperation(value = "Get list of books", response = BooksResponse.class, produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully fetched books"),
        @ApiResponse(code = 403, message = "Request forbidden"),
        @ApiResponse(code = 404, message = "Resource not found")
    })
    @GetMapping(path = "books")
    public ResponseEntity<BooksResponse> getAllBooks() {
        List<BookDto> books = bookService.fetchAllBooks();
        BooksResponse res = new BooksResponse();
        res.setBooks(books);
        return ResponseEntity.ok(res);
    }
}
