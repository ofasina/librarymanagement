/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class BookDTO {

    @NotBlank(message = "Title Cannot be null or blank. Required Field")
    @Size(max = 200, message = "Title should be less thaan or equal to 200 characters")
    private String title;
    @NotBlank(message = "Author Cannot be null or blank. Required Field")
    private String author;
    @NotBlank(message = "Publication year Cannot be null or blank. Required Field")
    @Pattern(regexp = "[0-9]{4}", message = "Publication Year should be in the format YYYY")
    private String publicationYear;
    @NotBlank(message = "ISBN Cannot be null or blank. Required Field")
    @Pattern(regexp = "[0-9]{10}", message = "only numeric values")
    private String isbn;
}
