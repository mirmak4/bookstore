/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.kazanik.basicfullstack.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.kazanik.basicfullstack.entity.Book;

/**
 *
 * @author miron.maksymiuk
 */
@Repository
// extends CrudRepository ???
public interface BookRepository extends JpaRepository<Book, Long>{
//public interface BookRepository extends CrudRepository<Book, Long>{
    
    Iterable<Book> findByTitle(String title);
    
    Iterable<Book> findByTitleIgnoreCase(String title);
}
