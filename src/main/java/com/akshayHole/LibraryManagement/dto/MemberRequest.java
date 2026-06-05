package com.akshayHole.LibraryManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRequest {

    @NotBlank(message = "Name is required")
    private String name;
    
    private String department;  // For faculty
    private String rollNumber;  // For student
}
