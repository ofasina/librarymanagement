/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class PatronDTO {
    
     @NotBlank(message = "Name cannot be blank, empty or null")
    private String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank, empty or null")
    private String email;
     @Pattern(regexp = "[0-9]{11}", message = "Mobile number should be 11 digits")
    @NotBlank(message = "MobileNumber be blank, empty or null")
    private String mobileNumber;
     @NotBlank(message = "Address cannot be blank, empty or null")
    private String address;
}
