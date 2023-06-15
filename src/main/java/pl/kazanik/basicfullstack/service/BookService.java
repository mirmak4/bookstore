/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.kazanik.basicfullstack.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kazanik.basicfullstack.dto.BookDto;
import pl.kazanik.basicfullstack.entity.Book;
import pl.kazanik.basicfullstack.repository.BookRepository;

/**
 *
 * @author miron.maksymiuk
 */
@Service
public class BookService {
    
    private  BookRepository bookRepository;
    private  ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<BookDto> fetchAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(convertBookModelToBookDto())
                .collect(Collectors.toList());
    }
    
    private Function<Book, BookDto> convertBookModelToBookDto() {
        return book -> modelMapper.map(book, BookDto.class);
    }
}
