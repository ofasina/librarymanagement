/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.contoller;

import com.workaround.eventproj.DTO.Credential;
import com.workaround.eventproj.DTO.LoginResponseDTO;
import com.workaround.eventproj.DTO.ResponseDTO;
import com.workaround.eventproj.DTO.User;
import com.workaround.eventproj.constant.Endpoints;
import com.workaround.eventproj.constant.ResponseCode;
import com.workaround.eventproj.security.JwtTokenUtil;
import com.workaround.eventproj.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
public class UserController {
    
     @Autowired
    private UserService userService;
     @Autowired
     JwtTokenUtil tokenService;
     @Autowired
    private AuthenticationManager authenticationManager; 
    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @PostMapping(value = Endpoints.CREATE_USER, consumes = JSON, produces = JSON)
    public ResponseDTO createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }
    
     @PostMapping(value = Endpoints.VALIDATE_USER, consumes = JSON, produces = JSON)
    public ResponseDTO authenticateAndGetToken(@RequestBody Credential authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())); 
        if (authentication.isAuthenticated()) { 
            LoginResponseDTO auth = new LoginResponseDTO();
            auth.setEmail(authRequest.getEmail());
            auth.setToken(tokenService.generateToken(authRequest));
            return new ResponseDTO(ResponseCode.OK, "OK" , auth); 
        } else { 
            return new ResponseDTO(ResponseCode.UNAUTHORIZED, "Unauthorized", null); 
        } 
    } 
}
