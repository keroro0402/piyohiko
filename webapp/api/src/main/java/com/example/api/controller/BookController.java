package com.example.api.controller;

import com.example.api.dto.BookDTO;
import com.example.api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

//    public BookController(BookService bookService){
//        this.bookService = bookService;
//    }

    @GetMapping("/book")
    public BookDTO getBook(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam int price
    ){

//        return new BookDTO(title, author,price);
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setTitle(title);
//        bookDTO.setAuthor(author);
//        bookDTO.setPrice(price);
//        return bookDTO;
        return bookService.getBook(title, author, price);
    }

    @PostMapping("/book")
    public BookDTO postBook(
            @RequestBody BookDTO bookDTO
    ){
        bookDTO.setTitle(bookDTO.getTitle().toUpperCase());
        return bookDTO;
    }
}

