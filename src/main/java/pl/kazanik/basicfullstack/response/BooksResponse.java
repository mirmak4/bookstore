/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kazanik.basicfullstack.dto.BookDto;

/**
 *
 * @author miron.maksymiuk
 */
@Getter
@Setter
@NoArgsConstructor
public class BooksResponse {
    
    private List<BookDto> books;
}
