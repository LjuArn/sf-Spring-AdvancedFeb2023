package com.example.demo.service;

import com.example.demo.domain.dto.AuthorDto;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entity.AuthorEntity;
import com.example.demo.domain.entity.BookEntity;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public List<BookDto> getAllBooks() {


        return bookRepository.findAll()
                .stream()
                .map(this::mapMetod)
                .toList();
    }

    private BookDto mapMetod(BookEntity bookEntity) {

        AuthorDto authorDto = new AuthorDto()
                .setName(bookEntity.getAuthor().getName());


        return new BookDto()
                .setId(bookEntity.getId())
                .setTitle(bookEntity.getTitle())
                .setIsbn(bookEntity.getIsbn())
                .setAuthor(authorDto);
    }


    public Optional<BookDto> getBookById(Long bookId) {
        return bookRepository.findById(bookId).map(this::mapMetod);
    }


    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public long createBook(BookDto newBook) {

        String authorName = newBook.getAuthor().getName();
        Optional<AuthorEntity> authorOpt = authorRepository.findAuthorEntityByName(authorName);

        BookEntity newBookEntity = new BookEntity()
                .setTitle(newBook.getTitle())
                .setIsbn(newBook.getIsbn())
                .setAuthor(authorOpt.isPresent()
                        ? authorOpt.get()
                        : createNewAuthor(authorName));


      return   bookRepository.save(newBookEntity).getId();
    }

    private AuthorEntity createNewAuthor(String authorName){
       return authorRepository.save(new AuthorEntity().setName(authorName));
    }

}
