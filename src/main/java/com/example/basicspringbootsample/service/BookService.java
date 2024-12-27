package com.example.basicspringbootsample.service;

import com.example.basicspringbootsample.domain.Book;
import com.example.basicspringbootsample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // Search
    public List<Book> getByKeyword(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book get(Long id) {
        return bookRepository.findById(id).get();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
