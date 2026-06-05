package com.akshayHole.LibraryManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("STUDENT")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudentMember extends Member {
    
    @Column(name = "roll_number", nullable = false)
    private String rollNumber;

    @Override
    public int getBorrowLimit() {
        return 10;
    }
}
