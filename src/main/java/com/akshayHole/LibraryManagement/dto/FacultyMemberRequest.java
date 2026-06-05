package com.akshayHole.LibraryManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FacultyMemberRequest {

    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Department is required")
    private String department;
}
