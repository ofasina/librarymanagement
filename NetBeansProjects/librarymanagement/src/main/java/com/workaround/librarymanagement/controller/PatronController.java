/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.controller;

import com.workaround.librarymanagement.DTO.PatronDTO;
import com.workaround.librarymanagement.constant.Endpoints;
import com.workaround.librarymanagement.service.PatronService;
import io.swagger.annotations.ApiOperation;
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
public class PatronController {

    private final PatronService patronService;
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @ApiOperation(value = "Create book Record", notes = "creates book entry")
    @PostMapping(value = Endpoints.CREATE_PATRON, consumes = JSON, produces = JSON)
    public ResponseEntity<?> createPatron(@RequestBody PatronDTO request, Authentication auth) {
        return patronService.createPatron(request, auth.getName());
    }

    @ApiOperation(value = "Get all the list of books", notes = "Returns a list of books")
    @GetMapping(value = Endpoints.ALL_PATRONS, produces = JSON)
    public ResponseEntity<?> allPatrons(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return patronService.fetchAllPatrons(page, size);
    }

    @ApiOperation(value = "Get book by id", notes = "Returns a details of the book")
    @GetMapping(value = Endpoints.PATRON_ID, produces = JSON)
    public ResponseEntity<?> getPatronById(@PathVariable(name = "id", required = true) long id) {
        return patronService.fetchPatronById(id);
    }

    @ApiOperation(value = "Delete a book")
    @DeleteMapping(value = Endpoints.DELETE_PATRON, consumes = JSON, produces = JSON)
    public ResponseEntity<?> deletePatron(@PathVariable(name = "id", required = true) long id) {
        return patronService.deletePatron(id);
    }

    @ApiOperation(value = "Update a book record", notes = "returns edited record")
    @PutMapping(value = Endpoints.UPDATE_PATRON, consumes = JSON, produces = JSON)
    public ResponseEntity<?> updatePatron(@RequestBody PatronDTO request, Authentication auth, @PathVariable(name = "id", required = true) long id) {
        return patronService.updatePatron(id, request, auth.getName());
    }
}
