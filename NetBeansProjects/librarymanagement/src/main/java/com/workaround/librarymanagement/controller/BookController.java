/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.controller;

import com.workaround.librarymanagement.DTO.BookDTO;
import com.workaround.librarymanagement.constant.Endpoints;
import com.workaround.librarymanagement.service.BookService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @ApiOperation(value = "Create book Record", notes="creates book entry")
    @PostMapping(value = Endpoints.CREATE_BOOK, consumes = JSON, produces = JSON)
    public ResponseEntity<?> createBook(@Valid @RequestBody BookDTO request, Authentication auth) {
        return bookService.createBook(request, auth.getName());
    }

        @ApiOperation(value = "Get all the list of books", notes="Returns a list of books")
    @GetMapping(value = Endpoints.ALL_BOOKS, produces = JSON)
    public ResponseEntity<?> allBooks(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return bookService.fetchAllBooks(page, size);
    }

     @ApiOperation(value = "Get book by id", notes="Returns a details of the book")
    @GetMapping(value = Endpoints.BOOK_ID, produces = JSON)
    public ResponseEntity<?> getBookById(@PathVariable(name = "id", required = true) long id) {
        return bookService.fetchBookById(id);
    }
    
      @ApiOperation(value = "Get book by id", notes="Returns a details of the book")
    @GetMapping(value = Endpoints.BOOK_TITLE, produces = JSON)
    public ResponseEntity<?> getBookByTitle(@PathVariable(name = "title", required = true) String title) {
        return bookService.fetchBookByTitle(title);
    }

     @ApiOperation(value = "Delete a book")
    @DeleteMapping(value = Endpoints.DELETE_BOOK, consumes = JSON, produces = JSON)
    public ResponseEntity<?> deleteBook(@PathVariable(name = "id", required = true) long id) {
        return bookService.deleteBook(id);
    }

     @ApiOperation(value = "Update a book record", notes="returns edited record")
    @PutMapping(value = Endpoints.UPDATE_BOOK, consumes = JSON, produces = JSON)
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookDTO request, Authentication auth, @PathVariable(name = "id", required = true) long id) {
        return bookService.updateBook(id, request, auth);
    }
}
