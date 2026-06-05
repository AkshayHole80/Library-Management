package com.akshayHole.LibraryManagement.repository;

import com.akshayHole.LibraryManagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
