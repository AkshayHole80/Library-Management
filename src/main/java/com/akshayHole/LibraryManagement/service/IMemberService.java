package com.akshayHole.LibraryManagement.service;

import com.akshayHole.LibraryManagement.dto.BookResponse;
import com.akshayHole.LibraryManagement.dto.FacultyMemberRequest;
import com.akshayHole.LibraryManagement.dto.MemberResponse;
import com.akshayHole.LibraryManagement.dto.StudentMemberRequest;
import com.akshayHole.LibraryManagement.entity.Member;

import java.util.List;

public interface IMemberService {
    MemberResponse createStudentMember(StudentMemberRequest request);
    MemberResponse createFacultyMember(FacultyMemberRequest request);
    List<MemberResponse> getAllMembers();
    Member findMemberById(Long memberId);
    List<BookResponse> getBorrowedBooks(Long memberId);
}
