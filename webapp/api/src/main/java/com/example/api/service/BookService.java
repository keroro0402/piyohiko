package com.example.api.service;

import com.example.api.dto.BookDTO;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    public BookDTO getBook(String title, String author, int price){
        return new BookDTO(title, author, price);
    }
}
