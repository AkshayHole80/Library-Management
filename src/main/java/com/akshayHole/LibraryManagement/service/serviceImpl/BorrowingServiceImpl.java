package com.akshayHole.LibraryManagement.service.serviceImpl;

import com.akshayHole.LibraryManagement.entity.Book;
import com.akshayHole.LibraryManagement.entity.Member;
import com.akshayHole.LibraryManagement.exception.BorrowingException;
import com.akshayHole.LibraryManagement.repository.BookRepository;
import com.akshayHole.LibraryManagement.repository.MemberRepository;
import com.akshayHole.LibraryManagement.service.IBorrowingService;
import com.akshayHole.LibraryManagement.service.IBookService;
import com.akshayHole.LibraryManagement.service.IMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowingServiceImpl implements IBorrowingService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final IMemberService memberService;
    private final IBookService bookService;

    @Override
    @Transactional
    public void borrowBook(Long memberId, Long bookId) {
        log.info("Member {} attempting to borrow book {}", memberId, bookId);
        
        Member member = memberService.findMemberById(memberId);
        Book book = bookService.findBookById(bookId);

        validateBorrowing(member, book);

        book.setQuantity(book.getQuantity() - 1);
        member.getBorrowedBooks().add(book);
        
        bookRepository.save(book);
        memberRepository.save(member);

        log.info("Book {} borrowed successfully by member {}", bookId, memberId);
    }

    @Override
    @Transactional
    public void returnBook(Long memberId, Long bookId) {
        log.info("Member {} attempting to return book {}", memberId, bookId);
        
        Member member = memberService.findMemberById(memberId);
        Book book = bookService.findBookById(bookId);

        if (!member.getBorrowedBooks().remove(book)) {
            throw new BorrowingException("Member did not borrow this book");
        }
        
        book.setQuantity(book.getQuantity() + 1);
        
        bookRepository.save(book);
        memberRepository.save(member);

        log.info("Book {} returned successfully by member {}", bookId, memberId);
    }

    private void validateBorrowing(Member member, Book book) {
        if (book.getQuantity() <= 0) {
            throw new BorrowingException("Book is not available");
        }
        if (member.getBorrowedBooks().size() >= member.getBorrowLimit()) {
            throw new BorrowingException("Member has reached the borrowing limit");
        }
    }
}
