/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pl.kazanik.basicfullstack.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kazanik.basicfullstack.entity.Book;
import pl.kazanik.basicfullstack.repository.BookRepository;
import org.modelmapper.ModelMapper;
import pl.kazanik.basicfullstack.dto.BookDto;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author miron.maksymiuk
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    
    @InjectMocks
    private BookService bookService;
    
    @Mock
    private BookRepository bookRepository;
    
    @Mock
    private ModelMapper mapper;
    
    private List<Book> books;
    private List<BookDto> bookDtos;
    
    @BeforeEach
    public void setUp() {
        Book book = getBook("Lord of the Rings", "J.R.R. Tolkien", 1951, "Trilogy");
        Book book2 = getBook("Dune", "Frank Herbert", 1960, "Part I");
        Book book3 = getBook("Hobbit", "J.R.R. Tolkien", 1947, "Story");
        books = new ArrayList<>();
        books.add(book);
        books.add(book2);
        books.add(book3);
        
        BookDto bookDto = getBookDto("Lord of the Rings", "J.R.R. Tolkien", 1951, "Trilogy");
        BookDto bookDto2 = getBookDto("Dune", "Frank Herbert", 1960, "Part I");
        BookDto bookDto3 = getBookDto("Hobbit", "J.R.R. Tolkien", 1947, "Story");
        bookDtos = new ArrayList<>();
        bookDtos.add(bookDto);
        bookDtos.add(bookDto2);
        bookDtos.add(bookDto3);
        
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of fetchAllBooks method, of class BookService.
     */
    @Test
    public void testFetchAllBooks() {
        Mockito.doReturn(bookDtos.get(0))
                .when(mapper).map(books.get(0), BookDto.class);
        Mockito.doReturn(bookDtos.get(1))
                .when(mapper).map(books.get(1), BookDto.class);
        Mockito.doReturn(bookDtos.get(2))
                .when(mapper).map(books.get(2), BookDto.class);
        Mockito.doReturn(books)
                .when(bookRepository).findAll();
        List<BookDto> fetchedBooks = bookService.fetchAllBooks();
        assertThat(fetchedBooks.size()).isEqualTo(3);
        assertThat(fetchedBooks.get(0))
            .hasFieldOrPropertyWithValue("title", "Lord of the Rings")
            .hasFieldOrPropertyWithValue("author", "J.R.R. Tolkien")
            .hasFieldOrPropertyWithValue("releaseYear", 1951)
            .hasFieldOrPropertyWithValue("description", "Trilogy");
        assertThat(fetchedBooks.get(1))
            .hasFieldOrPropertyWithValue("title", "Dune")
            .hasFieldOrPropertyWithValue("author", "Frank Herbert")
            .hasFieldOrPropertyWithValue("releaseYear", 1960)
            .hasFieldOrPropertyWithValue("description", "Part I");
        assertThat(fetchedBooks.get(2))
            .hasFieldOrPropertyWithValue("title", "Hobbit")
            .hasFieldOrPropertyWithValue("author", "J.R.R. Tolkien")
            .hasFieldOrPropertyWithValue("releaseYear", 1947)
            .hasFieldOrPropertyWithValue("description", "Story");
    }
    
    private Book getBook(String title, String author, int year, String description) {
        return Book.builder()
                .author(author)
                .title(title)
                .releaseYear(year)
                .description(description)
                .build();
    }
    
    private BookDto getBookDto(String title, String author, int year, String description) {
        return BookDto.builder()
                .author(author)
                .title(title)
                .releaseYear(year)
                .description(description)
                .build();
    }
}
