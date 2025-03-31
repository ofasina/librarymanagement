/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.service;

import com.workaround.librarymanagement.DTO.BookDTO;
import com.workaround.librarymanagement.model.Book;
import com.workaround.librarymanagement.repository.BookRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
@Validated
public class BookService {

    private final BookRepository bookRepo;

    public ResponseEntity<?> createBook(@Valid BookDTO request, String user) {
        try {
            Book newBook = new Book();
            BeanUtils.copyProperties(request, newBook);
            newBook.setCreatedAt(LocalDateTime.now());
            newBook.setCreatedBy(user);
            bookRepo.save(newBook);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> fetchAllBooks(int page, int size) {
        try {
            Pageable pagingSort = PageRequest.of(page, size);
            Page<Book> bookList = bookRepo.findAll(pagingSort);
            if (bookList.getContent().isEmpty()) {
                return new ResponseEntity<>(bookList.getContent(), HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<>(bookList.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> fetchBookById(long id) {
        try {
            Optional<Book> getBook = bookRepo.findById(id);
            if (getBook.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(getBook.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> fetchBookByTitle(String title) {
        try {
            Optional<Book> getBook = bookRepo.findByTitle(title);
            if (getBook.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(getBook.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> deleteBook(long id) {
        try {
            Optional<Book> getBook = bookRepo.findById(id);
            if (getBook.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            bookRepo.deleteById(id);
            return new ResponseEntity<>("Book Record deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> updateBook(long id, BookDTO request, Authentication auth) {
        try {
            Optional<Book> getBook = bookRepo.findById(id);
            if (getBook.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            BeanUtils.copyProperties(request, getBook.get());
            getBook.get().setUpdatedAt(LocalDateTime.now());
            getBook.get().setUpdatedBy(auth.getName());
            bookRepo.save(getBook.get());

            return new ResponseEntity<>(getBook.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
