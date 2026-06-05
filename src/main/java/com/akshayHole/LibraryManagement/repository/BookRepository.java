package com.akshayHole.LibraryManagement.repository;

import com.akshayHole.LibraryManagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
