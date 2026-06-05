package com.akshayHole.LibraryManagement.service.serviceImpl;

import com.akshayHole.LibraryManagement.dto.BookRequest;
import com.akshayHole.LibraryManagement.dto.BookResponse;
import com.akshayHole.LibraryManagement.entity.Book;
import com.akshayHole.LibraryManagement.exception.ResourceNotFoundException;
import com.akshayHole.LibraryManagement.repository.BookRepository;
import com.akshayHole.LibraryManagement.service.IBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookResponse createBook(BookRequest request) {
        log.info("Creating book: {}", request.getTitle());
        Book book = modelMapper.map(request, Book.class);
        Book saved = bookRepository.save(book);
        return modelMapper.map(saved, BookResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book:", bookId));
    }
}
