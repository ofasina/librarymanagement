/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.DTO;

import com.workaround.eventproj.constant.Category;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class EventResponseDTO {
    
    private int id;
    private String name;
    private String description;
    private LocalDate date;
    private int availableAttendeesCount;
    private Category category;
}
