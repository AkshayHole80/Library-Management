package com.akshayHole.LibraryManagement.entity;

import com.akshayHole.LibraryManagement.enums.BookType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String publisher;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_type", nullable = false)
    private BookType bookType;

    @Column(nullable = false)
    private int quantity;
}
