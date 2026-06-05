package com.akshayHole.LibraryManagement.dto;

import com.akshayHole.LibraryManagement.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private BookType bookType;
    private int quantity;
}
