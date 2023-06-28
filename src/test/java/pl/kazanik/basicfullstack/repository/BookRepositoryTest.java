/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.repository;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.kazanik.basicfullstack.entity.Book;
import pl.kazanik.basicfullstack.testutils.TestUtils;

/**
 *
 * @author miron.maksymiuk
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
    
    @Autowired
    private BookRepository bookRepository;
  
    @Test
    @Sql(scripts = "classpath:LordOfTheRings.sql")
    public void shouldFetchAllBooksFromDB() {
        Iterable<Book> books = bookRepository.findAll();
        long size = TestUtils.getCrudRepoIterableSize(books);
        Assertions.assertEquals(3l, size);
    }
    
    @Test
    @Sql(scripts = "classpath:LordOfTheRings.sql")
    public void shouldFindBookByTitle() {
        String bookTitle = "Lord of the Rings: Fellowship of the Ring";
        Iterable<Book> books = bookRepository.findByTitle(bookTitle);
        long size = TestUtils.getCrudRepoIterableSize(books);
        Assertions.assertEquals(1l, size);
    }
    
    @Test
    @Sql(scripts = "classpath:LordOfTheRings.sql")
    public void shouldFindBookByTitleIgnoreCase() {
        String bookTitle = "lord of the Rings: Fellowship of the Ring";
        Iterable<Book> books = bookRepository.findByTitleIgnoreCase(bookTitle);
        long size = TestUtils.getCrudRepoIterableSize(books);
        Assertions.assertEquals(1l, size);
    }
}
