/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.controller;

import com.workaround.librarymanagement.DTO.AuthDTO;
import com.workaround.librarymanagement.DTO.Credential;
import com.workaround.librarymanagement.DTO.User;
import com.workaround.librarymanagement.constant.Endpoints;
import com.workaround.librarymanagement.security.JwtTokenUtil;
import com.workaround.librarymanagement.service.UserManagementService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
@RequiredArgsConstructor
public class UserAuthenticationController {
    
    private final AuthenticationManager authenticationManager; 
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;
    private final JwtTokenUtil tokenService;
    private final UserManagementService userService;
    
    @PostMapping(value = Endpoints.CREATE_USER, consumes = JSON, produces = JSON)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return userService.createUserProfile(user);
    }
    
     @PostMapping(value = Endpoints.VALIDATE_USER, consumes = JSON, produces = JSON)
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody Credential authRequest) { 
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            AuthDTO auth = new AuthDTO();
            auth.setEmail(authRequest.getEmail());
            auth.setToken(tokenService.generateToken(authRequest));
            return new ResponseEntity<>(auth, HttpStatus.OK); 
        } else { 
            return new ResponseEntity<>("Bad Credentials", HttpStatus.UNAUTHORIZED); 
        } 
        }catch(Exception e){
                 return new ResponseEntity<>(e.getMessage().equalsIgnoreCase("Bad Credentials"), 
                         HttpStatus.UNPROCESSABLE_ENTITY); 
        
        }
    } 
}
