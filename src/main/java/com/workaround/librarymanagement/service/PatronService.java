/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.service;

import com.workaround.librarymanagement.DTO.PatronDTO;
import com.workaround.librarymanagement.model.Patron;
import com.workaround.librarymanagement.repository.PatronRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class PatronService {
    
       private final PatronRepository patronRepo;

    public ResponseEntity<?> createPatron(PatronDTO request, String user) {
        try {
            Patron newPatron = new Patron();
            BeanUtils.copyProperties(request, newPatron);
            newPatron.setCreatedAt(LocalDateTime.now());
            newPatron.setCreatedBy(user);
            patronRepo.save(newPatron);
            return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> fetchAllPatrons(int page, int size) {
        try {
            Pageable pagingSort = PageRequest.of(page, size);
            Page<Patron> patronList = patronRepo.findAll(pagingSort);
            if (patronList.getContent().isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<>(patronList.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> fetchPatronById(long id) {
        try {
            Optional<Patron> getPatron = patronRepo.findById(id);
            if (getPatron.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(getPatron.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> deletePatron(long id) {
        try {
            Optional<Patron> getPatron = patronRepo.findById(id);
            if (getPatron.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            patronRepo.deleteById(id);
            return new ResponseEntity<>("Patron Record deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> updatePatron(long id, PatronDTO request, String user) {
        try {
            Optional<Patron> getPatron = patronRepo.findById(id);
            if (getPatron.isEmpty()) {
                return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
            }
            BeanUtils.copyProperties(request, getPatron.get());
            getPatron.get().setUpdatedAt(LocalDateTime.now());
            getPatron.get().setUpdatedBy(user);
            patronRepo.save(getPatron.get());

            return new ResponseEntity<>(getPatron.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
