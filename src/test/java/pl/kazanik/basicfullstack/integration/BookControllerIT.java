/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.integration;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;
import pl.kazanik.basicfullstack.BasicfullstackApplication;
import pl.kazanik.basicfullstack.dto.BookDto;
import pl.kazanik.basicfullstack.response.BooksResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.web.server.LocalServerPort;

/**
 *
 * @author miron.maksymiuk
 */
@SpringBootTest(classes = BasicfullstackApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@AutoConfigureTestDatabase(replace = Replace.ANY)
public class BookControllerIT {
    
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemple;
    private String baseUrl = "http://localhost:%d/bookstore/api/v1";
    
    @Test
    @Sql(scripts = "classpath:cleandb.sql")
    @Sql(scripts = "classpath:LordOfTheRings.sql")
    public void shouldFetchAllBooksWhenApiCalled() {
        String resourcePath = "/books";
        String url = baseUrl.formatted(port)+resourcePath;
        BooksResponse res = restTemple.getForObject(url, BooksResponse.class);
        List<BookDto> books = res.getBooks();
        assertThat(books).isNotNull().hasSize(3);
    }
    
    @Test
    @Sql(scripts = "classpath:cleandb.sql")
    @Sql(scripts = "classpath:LordOfTheRings.sql")
    public void shouldFetchAllBooksWhenApiCalled2() {
        String resourcePath = "/books";
        String url = baseUrl.formatted(port)+resourcePath;
        BooksResponse res = restTemple.getForObject(url, BooksResponse.class);
        List<BookDto> books = res.getBooks();
        assertThat(books).isNotNull().hasSize(3);
    }
    
    @Test
    @Sql(scripts = "classpath:cleandb.sql")
    @Sql(scripts = "classpath:LordOfTheRings.sql")
    public void shouldFetchBooksByTitleWhenApiCalled() {
        String title = "Lord of the Rings: Fellowship of the Ring";
        String author = "J.R.R. Tolkien";
        int year = 1951;
        String resourcePath = "/books";
        String url = baseUrl.formatted(port)+resourcePath+"/"+title.toUpperCase();
        BooksResponse res = restTemple.getForObject(url, BooksResponse.class);
        List<BookDto> books = res.getBooks();
        assertThat(books).isNotNull().hasSize(1);
        assertThat(books.get(0)).hasFieldOrPropertyWithValue("title", title);
        assertThat(books.get(0)).hasFieldOrPropertyWithValue("author", author);
        assertThat(books.get(0)).hasFieldOrPropertyWithValue("releaseYear", year);
    }
}
