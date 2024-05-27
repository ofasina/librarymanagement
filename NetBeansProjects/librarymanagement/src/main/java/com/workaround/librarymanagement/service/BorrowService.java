/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.service;

import com.workaround.librarymanagement.model.Book;
import com.workaround.librarymanagement.model.BorrowRecord;
import com.workaround.librarymanagement.model.Patron;
import com.workaround.librarymanagement.repository.BookRepository;
import com.workaround.librarymanagement.repository.BorrowRepository;
import com.workaround.librarymanagement.repository.PatronRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepo;
    private final PatronRepository patronRepo;

    public ResponseEntity<?> borrowBook(long bookId, long patronId, Authentication auth) {
        try {
            // check that book exist
            Optional<Book> existingook = bookRepo.findById(bookId);
            if (existingook.isEmpty()) {
                return new ResponseEntity<>("No book record found", HttpStatus.NOT_FOUND);
            }
            // confirm that patron exist
            Optional<Patron> existingPatron = patronRepo.findById(patronId);
            if (existingPatron.isEmpty()) {
                return new ResponseEntity<>("No patron record found", HttpStatus.NOT_FOUND);
            }
            BorrowRecord borrow = new BorrowRecord();
            borrow.setBook(existingook.get());
            borrow.setPatron(existingPatron.get());
            borrow.setBookGivenBy(auth.getName());
            borrow.setBorrowedAt(LocalDateTime.now());
            borrow.setStatus("Borrowed");
            borrowRepository.save(borrow);
            return new ResponseEntity<>(borrow, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    public ResponseEntity<?> returnBook(long bookId, long patronId, Authentication auth) {
        try {
//            // check that book exist
            Optional<Book> existingook = bookRepo.findById(bookId);
            if (existingook.isEmpty()) {
                return new ResponseEntity<>("No book record found", HttpStatus.NOT_FOUND);
            }
            // confirm that patron exist
            Optional<Patron> existingPatron = patronRepo.findById(patronId);
            if (existingPatron.isEmpty()) {
                return new ResponseEntity<>("No patron record found", HttpStatus.NOT_FOUND);
            }
            // exiting borrowed book
            Optional<BorrowRecord> borrowedRec = borrowRepository.findByBookAndPatron
        (existingook.get(), existingPatron.get());
            if (borrowedRec.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            borrowedRec.get().setBookRetrievedBy(auth.getName());
            borrowedRec.get().setReturnedAt(LocalDateTime.now());
            borrowedRec.get().setStatus("Returned");
            borrowRepository.save(borrowedRec.get());
            return new ResponseEntity<>(borrowedRec.get(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
