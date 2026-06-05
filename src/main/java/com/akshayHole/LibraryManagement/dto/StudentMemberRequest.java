package com.akshayHole.LibraryManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentMemberRequest {

    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Roll number is required")
    private String rollNumber;
}
