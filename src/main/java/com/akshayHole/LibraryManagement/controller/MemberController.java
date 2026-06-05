package com.akshayHole.LibraryManagement.controller;

import com.akshayHole.LibraryManagement.dto.ApiMessageResponse;
import com.akshayHole.LibraryManagement.dto.BookResponse;
import com.akshayHole.LibraryManagement.dto.FacultyMemberRequest;
import com.akshayHole.LibraryManagement.dto.MemberResponse;
import com.akshayHole.LibraryManagement.dto.StudentMemberRequest;
import com.akshayHole.LibraryManagement.service.IBorrowingService;
import com.akshayHole.LibraryManagement.service.IMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Member Controller", description = "Endpoints for managing members and borrowing/returning books")
@Slf4j
public class MemberController {

    private final IMemberService memberService;
    private final IBorrowingService borrowingService;

    @PostMapping("/student")
    @Operation(summary = "Create a new student member")
    public ResponseEntity<MemberResponse> createStudentMember(@Valid @RequestBody StudentMemberRequest request) {
        log.info("REST request to create student member: {}", request.getName());
        MemberResponse response = memberService.createStudentMember(request);
        return ResponseEntity
                .created(URI.create("/api/members/" + response.getId()))
                .body(response);
    }

    @PostMapping("/faculty")
    @Operation(summary = "Create a new faculty member")
    public ResponseEntity<MemberResponse> createFacultyMember(@Valid @RequestBody FacultyMemberRequest request) {
        log.info("REST request to create faculty member: {}", request.getName());
        MemberResponse response = memberService.createFacultyMember(request);
        return ResponseEntity
                .created(URI.create("/api/members/" + response.getId()))
                .body(response);
    }

    @GetMapping
    @Operation(summary = "Get all members")
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PostMapping("/{memberId}/borrow/{bookId}")
    @Operation(summary = "Borrow a book")
    public ResponseEntity<ApiMessageResponse> borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        log.info("REST request: member {} borrowing book {}", memberId, bookId);
        borrowingService.borrowBook(memberId, bookId);
        return ResponseEntity.ok(new ApiMessageResponse("Book borrowed successfully"));
    }

    @GetMapping("/{memberId}/borrowed-books")
    @Operation(summary = "Get borrowed books by member ID")
    public ResponseEntity<List<BookResponse>> getBorrowedBooks(@PathVariable Long memberId) {
        log.info("REST request to get borrowed books for member: {}", memberId);
        return ResponseEntity.ok(memberService.getBorrowedBooks(memberId));
    }
}
