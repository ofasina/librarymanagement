/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.eventproj.repository;


import com.workaround.eventproj.model.ReservedTicket;
import com.workaround.eventproj.model.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface ReservedTicketRepository extends JpaRepository<ReservedTicket, Long>{
    
    List<ReservedTicket> findByUserAndStatus(Users user, String status);
    
    Optional<ReservedTicket> findByIdAndStatus(long ticketId, String status);
}
