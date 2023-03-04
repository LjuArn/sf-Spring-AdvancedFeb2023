package com.example.demo.web;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {


    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());


    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {
        Optional<BookDto> theBook = bookService.getBookById(bookId);


        return theBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("id") Long bookId) {
        bookService.deleteById(bookId);

        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto newBook,
                                              UriComponentsBuilder uriComponentsBuilder){

        long newBookId = bookService.createBook(newBook);

        return ResponseEntity.created(uriComponentsBuilder.path("/api/books/{id}")
                .build(newBookId)).build();
    }
}
