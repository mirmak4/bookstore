/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author miron.maksymiuk
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    
    private Long id;
    
    private String title;
    
    private String description;
    
    private String author;
    
    private Integer releaseYear;
}
