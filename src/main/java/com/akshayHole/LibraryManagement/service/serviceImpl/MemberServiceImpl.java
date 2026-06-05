package com.akshayHole.LibraryManagement.service.serviceImpl;

import com.akshayHole.LibraryManagement.dto.BookResponse;
import com.akshayHole.LibraryManagement.dto.FacultyMemberRequest;
import com.akshayHole.LibraryManagement.dto.MemberResponse;
import com.akshayHole.LibraryManagement.dto.StudentMemberRequest;
import com.akshayHole.LibraryManagement.entity.Book;
import com.akshayHole.LibraryManagement.entity.FacultyMember;
import com.akshayHole.LibraryManagement.entity.Member;
import com.akshayHole.LibraryManagement.entity.StudentMember;
import com.akshayHole.LibraryManagement.exception.ResourceNotFoundException;
import com.akshayHole.LibraryManagement.repository.MemberRepository;
import com.akshayHole.LibraryManagement.service.IMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements IMemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public MemberResponse createStudentMember(StudentMemberRequest request) {
        log.info("Creating student member: {}", request.getName());
        StudentMember member = new StudentMember();
        member.setName(request.getName());
        member.setRollNumber(request.getRollNumber());
        Member saved = memberRepository.save(member);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public MemberResponse createFacultyMember(FacultyMemberRequest request) {
        log.info("Creating faculty member: {}", request.getName());
        FacultyMember member = new FacultyMember();
        member.setName(request.getName());
        member.setDepartment(request.getDepartment());
        Member saved = memberRepository.save(member);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member", memberId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getBorrowedBooks(Long memberId) {
        Member member = findMemberById(memberId);
        return member.getBorrowedBooks().stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }

    private MemberResponse toResponse(Member member) {
        MemberResponse response = modelMapper.map(member, MemberResponse.class);
        response.setMemberCategory(resolveCategory(member));
        response.setBorrowedBookIds(member.getBorrowedBooks().stream()
                .map(Book::getId)
                .toList());
        
        if (member instanceof StudentMember) {
            response.setRollNumber(((StudentMember) member).getRollNumber());
        } else if (member instanceof FacultyMember) {
            response.setDepartment(((FacultyMember) member).getDepartment());
        }
        
        return response;
    }

    private String resolveCategory(Member member) {
        if (member instanceof StudentMember) return "STUDENT";
        if (member instanceof FacultyMember) return "FACULTY";
        return "UNKNOWN";
    }
}
