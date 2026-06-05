package com.akshayHole.LibraryManagement.service;

public interface IBorrowingService {
    void borrowBook(Long memberId, Long bookId);
    void returnBook(Long memberId, Long bookId);
}
