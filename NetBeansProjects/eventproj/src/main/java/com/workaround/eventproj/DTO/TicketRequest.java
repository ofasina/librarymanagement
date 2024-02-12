/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.DTO;

import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Olawale
 */
@Data
public class TicketRequest {
    
    @Size(min= 1, message="Minimum attendees is 1")
    private int attendeesCount;
}
