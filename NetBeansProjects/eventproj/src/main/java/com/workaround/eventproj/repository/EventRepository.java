/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.eventproj.repository;

import com.workaround.eventproj.model.Events;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface EventRepository extends JpaRepository<Events, Long>{
    Page<Events> findByName(String name, Pageable page);
    
    Page<Events> findByDateBetween(LocalDate startDat, LocalDate endDate, Pageable page);
    
     Page<Events> findByCategory(String category, Pageable page);
    
}
