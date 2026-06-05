package com.akshayHole.LibraryManagement.dto;

import com.akshayHole.LibraryManagement.enums.BookType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    private String publisher;

    @NotNull(message = "Book type is required")
    private BookType bookType;

    @Min(value = 0, message = "Quantity must be zero or positive")
    private int quantity;
}
