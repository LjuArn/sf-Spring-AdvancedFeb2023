package com.example.demo.web;

import com.example.demo.domain.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){



    }

}
