/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.controller;

import com.workaround.librarymanagement.constant.Endpoints;
import com.workaround.librarymanagement.service.BorrowService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
@RequiredArgsConstructor
public class BorrowContoller {

    private final BorrowService borrowService;
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

     @ApiOperation(value = "Borrow book")
    @PostMapping(value = Endpoints.BORROW_BOOK, consumes = JSON, produces = JSON)
    public ResponseEntity<?> borrowBook(@PathVariable(name = "bookId", required = true) long bookId,
            @PathVariable(name = "patronId", required = true) long patronId,
            Authentication auth) {
        return borrowService.borrowBook(bookId, patronId, auth);
    }

     @ApiOperation(value = "Returns book")
    @PutMapping(value = Endpoints.RETURN_BOOK, consumes = JSON, produces = JSON)
    public ResponseEntity<?> returnBook(
            @PathVariable(name = "bookId", required = true) long bookId,
            @PathVariable(name = "patronId", required = true) long patronId,
            Authentication auth) {
        return borrowService.returnBook(bookId, patronId, auth);
    }
}
