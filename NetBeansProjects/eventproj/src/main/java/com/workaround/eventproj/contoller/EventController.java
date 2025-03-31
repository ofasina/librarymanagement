/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.contoller;

import com.workaround.eventproj.DTO.EventRequestDTO;
import com.workaround.eventproj.DTO.ResponseDTO;
import com.workaround.eventproj.DTO.TicketRequest;
import com.workaround.eventproj.constant.Endpoints;
import com.workaround.eventproj.service.EventService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Olawale
 */
@RestController
public class EventController {

    @Autowired
    EventService eventService;

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @PostMapping(value = Endpoints.CREATE_EVENTS, consumes = JSON, produces = JSON)
    public ResponseDTO createEvent(@RequestBody EventRequestDTO user) {
        return eventService.createEvent(user);
    }

    @GetMapping(value = Endpoints.GET_EVENTS, produces = JSON)
    public ResponseDTO searchEvents(@RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String category) {

        return eventService.searchEvents(name, startDate, endDate, category, page, size);
    }
    

    @PostMapping(value = Endpoints.RESERVE_TICKETS_EVENTS, consumes = JSON, produces = JSON)
    public ResponseDTO reserveTicket(@PathVariable(name = "eventId", required = true) long eventId,
            @RequestBody TicketRequest ticket,
            @RequestHeader("Authorization") String token) {
        return eventService.reserveTicket(eventId, ticket, token);
    }
    
    
    @PostMapping(value = Endpoints.VIEW_BOOKED_EVENTS, consumes = JSON, produces = JSON)
    public ResponseDTO viewReservedTickets(@PathVariable(name = "email", required = true) String email) {
        return eventService.viewReservedTickets(email);
    }
    
    
    @PostMapping(value = Endpoints.CANCEL_BOOKED_EVENTS, consumes = JSON, produces = JSON)
    public ResponseDTO cancelTicket(@PathVariable(name = "ticketId", required = true) long ticketId) {
        return eventService.cancelReservedTickets(ticketId);
    }

}
