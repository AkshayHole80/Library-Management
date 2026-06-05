package com.akshayHole.LibraryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private String memberCategory;
    private int borrowLimit;
    private String department;  // For faculty
    private String rollNumber;  // For student
    private List<Long> borrowedBookIds;
}
