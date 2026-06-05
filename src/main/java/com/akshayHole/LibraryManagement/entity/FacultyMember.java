package com.akshayHole.LibraryManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("FACULTY")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FacultyMember extends Member {
    
    @Column(name = "department", nullable = false)
    private String department;

    @Override
    public int getBorrowLimit() {
        return 5;
    }
}
