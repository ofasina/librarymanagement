/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.service;

import com.workaround.librarymanagement.DTO.User;
import com.workaround.librarymanagement.model.Users;
import com.workaround.librarymanagement.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRepository userRepo;
    //private final UserRoleRepository userRoleRepo;
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<?> createUserProfile(User request) {

        //check for exiting user
        Optional<Users> existingUser = userRepo.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseEntity <> ("Existing user with email", HttpStatus.BAD_REQUEST);
        }
        // check for exiting role
//        Optional<UserRole> existingRole = userRoleRepo.findByRole(request.getRole());
//        if (existingRole.isEmpty()) {
//            return new ResponseDTO(HttpStatus.NOT_FOUND.toString(), "Role not found", null);
//        }
        Users newUser = new Users();
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setCreatedBy("SysAdm");
        newUser.setEmail(request.getEmail());
        newUser.setName(request.getName());
        newUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
//        newUser.setUserRole(request.getRole());
        userRepo.save(newUser);
          return new ResponseEntity<> (newUser, HttpStatus.CREATED);
        
    }
}
