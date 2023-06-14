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
import pl.kazanik.basicfullstack.dto.BookDto;

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
        List<BookDto> books = bookRepository.findAll();
        int size = books.size();
        Assertions.assertEquals(size, 3);
    }
    
    @Test
    @Sql(scripts = "classpath:LordOfTheRings.sql")
    public void shouldFindBookByTitle() {
        String bookTitle = "Lord of the Rings: Fellowship of the Ring";
        List<BookDto> books = bookRepository.findByTitle(bookTitle);
        int size = books.size();
        Assertions.assertEquals(size, 1);
    }
}
