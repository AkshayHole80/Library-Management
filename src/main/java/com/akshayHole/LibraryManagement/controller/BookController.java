package com.akshayHole.LibraryManagement.controller;

import com.akshayHole.LibraryManagement.dto.BookRequest;
import com.akshayHole.LibraryManagement.dto.BookResponse;
import com.akshayHole.LibraryManagement.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book Controller", description = "Endpoints for managing books")
@Slf4j
public class BookController {

    private final IBookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        log.info("REST request to create book: {}", request.getTitle());
        BookResponse response = bookService.createBook(request);
        return ResponseEntity
                .created(URI.create("/api/books/" + response.getId()))
                .body(response);
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
