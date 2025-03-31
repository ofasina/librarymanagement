/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.service;

import com.workaround.eventproj.DTO.ResponseDTO;
import com.workaround.eventproj.DTO.User;
import com.workaround.eventproj.constant.ResponseCode;
import com.workaround.eventproj.model.Users;
import com.workaround.eventproj.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
public class UserService {
    
     @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseDTO createUser(User request) {
        // check that user doesn't exist with same email
        Optional<Users> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return new ResponseDTO(ResponseCode.BADREQUEST, "Existing user with email", null);
        }

        Users create = new Users();
        create.setEmail(request.getEmail());
        create.setName(request.getName());
        create.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        create.setCreatedAt(LocalDateTime.now());

        userRepository.save(create);

        return new ResponseDTO(ResponseCode.CREATED, "Created", create);
    }
    
}
