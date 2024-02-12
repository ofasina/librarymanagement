/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.service;

import com.workaround.eventproj.repository.EventRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
public class ScheduleService {
    
    @Autowired
    private EventRepository eventRepository;
    
    
    //@Scheduled(fixedDelay = 60000)
    public void notifyForUpcominEvent(){
         LocalDate today = LocalDate.now();
         
    }
}
