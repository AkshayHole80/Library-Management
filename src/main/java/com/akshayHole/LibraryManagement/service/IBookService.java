package com.akshayHole.LibraryManagement.service;

import com.akshayHole.LibraryManagement.dto.BookRequest;
import com.akshayHole.LibraryManagement.dto.BookResponse;
import com.akshayHole.LibraryManagement.entity.Book;

import java.util.List;

public interface IBookService {
    BookResponse createBook(BookRequest request);
    List<BookResponse> getAllBooks();
    Book findBookById(Long bookId);
}
