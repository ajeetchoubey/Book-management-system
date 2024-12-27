package com.example.basicspringbootsample.repository;

import com.example.basicspringbootsample.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Search
    @Query(value = "SELECT * FROM Book b WHERE b.book_name LIKE %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);

}
