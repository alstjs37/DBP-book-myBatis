package com.example.bookmybatis.service;

import com.example.bookmybatis.domain.Book;
import com.example.bookmybatis.entity.BookEntity;
import com.example.bookmybatis.repository.MybatisBookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final MybatisBookRepository bookRepository;

    public BookService(MybatisBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 전체 도서 목록 조회
    public List<Book.Simple> findBooks() {
        List<Book.Simple> list = new ArrayList<>();
        for(BookEntity bookEntity: bookRepository.findAll()) {
            Book.Simple book = new Book.Simple();
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setPublisher(bookEntity.getPublisher());
            book.setPrice(bookEntity.getPrice());
            list.add(book);
        }
        return list;
    }

    public Long addBook(Book.Create bookForm) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookForm.getName());
        bookEntity.setPublisher(bookForm.getPublisher());
        bookEntity.setPrice(bookForm.getPrice());
        bookRepository.save(bookEntity);
        return bookEntity.getId();
    }
}
