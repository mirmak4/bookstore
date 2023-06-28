/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pl.kazanik.basicfullstack.controller;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.kazanik.basicfullstack.dto.BookDto;
import pl.kazanik.basicfullstack.response.BooksResponse;
import pl.kazanik.basicfullstack.service.BookService;
import static org.mockito.ArgumentMatchers.anyString;

/**
 *
 * @author miron.maksymiuk
 */
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    
    @InjectMocks
    private BookController bookController;
    @Mock
    private BookService bookService;
    private List<BookDto> books;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        BookDto bookDto = new BookDto(1l, "Lord of the Rings", "Trilogy", "J.R.R. Tolkien", 1951);
        BookDto bookDto2 = new BookDto(2l, "Dune", "Part I", "Frank Herbert", 1960);
        BookDto bookDto3 = new BookDto(3l, "Hobbit", "Story", "J.R.R. Tolkien", 1947);
        books = new ArrayList<>();
        books.add(bookDto);
        books.add(bookDto2);
        books.add(bookDto3);
    }

    /**
     * Test of getAllBooks method, of class BookController.
     */
    @Test
    public void testGetAllBooks() {
        Mockito.doReturn(books).when(bookService).fetchAllBooks();
        ResponseEntity<BooksResponse> res = bookController.getAllBooks();
        List<BookDto> books = res.getBody().getBooks();
        Assertions.assertThat(3).isEqualTo(books.size());
    }
    
    @Test
    public void testGetAllBooksByTitle() {
        Mockito.doReturn(books).when(bookService).fetchBooksByTitle(anyString());
        ResponseEntity<BooksResponse> res = bookController.getBooksByTitle("test title");
        List<BookDto> books = res.getBody().getBooks();
        Assertions.assertThat(3).isEqualTo(books.size());
    }
    
}
