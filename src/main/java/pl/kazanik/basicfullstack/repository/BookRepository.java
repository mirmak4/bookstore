/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.kazanik.basicfullstack.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kazanik.basicfullstack.dto.BookDto;

/**
 *
 * @author miron.maksymiuk
 */
@Repository
public interface BookRepository extends JpaRepository<BookDto, Long>{
    
    List<BookDto> findByTitle(String title);
}
