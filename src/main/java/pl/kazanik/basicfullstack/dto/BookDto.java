/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "BookDto", description = "Details about book")
public class BookDto {
    
    @ApiModelProperty(
            readOnly = true,
            value = "id",
            dataType = "Integer",
            example = "1",
            notes = "Database generated book id",
            required = true
    )
//    private Long id;
    private Integer id;
    
    @ApiModelProperty(
            value = "title",
            dataType = "String",
            example = "Lord of the Rings",
            notes = "Book title",
            required = true
    )
    private String title;
    
    @ApiModelProperty(
            value = "description",
            dataType = "String",
            example = "History of MiddleEarth",
            notes = "Book description",
            required = true
    )
    private String description;
    
    @ApiModelProperty(
            value = "author",
            dataType = "String",
            example = "J.R.R. Tolkien",
            notes = "Book author",
            required = true
    )
    private String author;
    
    @ApiModelProperty(
            value = "releaseYear",
            dataType = "Integer",
            example = "1951",
            notes = "Book release year",
            required = true
    )
    private Integer releaseYear;
}
