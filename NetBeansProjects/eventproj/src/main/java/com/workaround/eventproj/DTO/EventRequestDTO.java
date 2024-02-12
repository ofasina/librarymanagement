/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.DTO;

import com.workaround.eventproj.constant.Category;
import java.time.LocalDate;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class EventRequestDTO {
    
    private String name;
    private LocalDate date;
    
    @Size(min = 0, max = 1000, message="Maximum available attendee is 1000")
    private int availableAttendeesCount;
    private Category category;
    private String description;
}
