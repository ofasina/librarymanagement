/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.service;

import com.workaround.eventproj.DTO.EventRequestDTO;
import com.workaround.eventproj.DTO.ResponseDTO;
import com.workaround.eventproj.DTO.TicketRequest;
import com.workaround.eventproj.constant.ResponseCode;
import com.workaround.eventproj.constant.TicketStatus;
import com.workaround.eventproj.model.Events;
import com.workaround.eventproj.model.ReservedTicket;
import com.workaround.eventproj.model.Users;
import com.workaround.eventproj.repository.EventRepository;
import com.workaround.eventproj.repository.ReservedTicketRepository;
import com.workaround.eventproj.repository.UserRepository;
import com.workaround.eventproj.security.JwtTokenUtil;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Olawale
 */
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ReservedTicketRepository reservedTicketsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtTokenUtil tokenUtil;

    public ResponseDTO createEvent(EventRequestDTO request) {

        try {
            Events newEvent = new Events();
            newEvent.setCategory(request.getCategory().name());
            newEvent.setName(request.getName());
            newEvent.setDate(request.getDate());
            newEvent.setEventDescription(request.getDescription());
            newEvent.setMaxAttendees(request.getAvailableAttendeesCount());
            newEvent.setStatus("UPCOMING");

            eventRepository.save(newEvent);
            return new ResponseDTO(ResponseCode.CREATED, "Created", newEvent.getId());

        } catch (Exception e) {
            return new ResponseDTO(ResponseCode.SYSTEM_MALFUNCTION, e.getMessage(),
                    null);
        }
    }

    public ResponseDTO searchEvents(String name, LocalDate startDate, LocalDate endDate, String category, int page, int size) {

        Pageable pagingSort = PageRequest.of(page, size, Sort.Direction.DESC);
        Page<Events> events = eventRepository.findAll(pagingSort);
        if (name != null && !name.isEmpty()) {
            return new ResponseDTO(ResponseCode.OK, "Ok", eventRepository.findByName(name, pagingSort));
        }

        if (startDate != null & endDate != null) {
            return new ResponseDTO(ResponseCode.OK, "Ok",
                    eventRepository.findByDateBetween(startDate, endDate, pagingSort));
        }

        if (category != null && !category.isEmpty()) {
            return new ResponseDTO(ResponseCode.OK, "Ok", eventRepository.findByCategory(category, pagingSort));
        }
        return new ResponseDTO(ResponseCode.OK, "Ok", events);

    }

    public ResponseDTO reserveTicket(long eventId, TicketRequest request, String token) {
        try {
            String email = tokenUtil.getEmailFromToken(token);
            Optional<Users> user = userRepository.findByEmail(email);
            if (request.getAttendeesCount() == 0) {
                return new ResponseDTO(ResponseCode.BADREQUEST, "Bad Request", null);
            }
            ReservedTicket tickets = new ReservedTicket();
            // check that event has not exceeded the maximum number of attendees
            Optional<Events> events = eventRepository.findById(eventId);
            if (events.isPresent()) {
                if (events.get().getReservedTickets() == events.get().getMaxAttendees()) {
                    return new ResponseDTO(ResponseCode.BADREQUEST, "Event fully booked", null);
                }

                tickets.setCreatedAt(LocalDateTime.now());
                tickets.setEvents(events.get());
                tickets.setTicketReserved(request.getAttendeesCount());
                tickets.setUser(user.get());
                reservedTicketsRepository.save(tickets);

                events.get().setReservedTickets(request.getAttendeesCount());

            }
            return new ResponseDTO(ResponseCode.CREATED, "Created", tickets);
        } catch (Exception e) {
            return new ResponseDTO(ResponseCode.SYSTEM_MALFUNCTION, e.getMessage(), null);
        }
    }

    public ResponseDTO viewReservedTickets(String email) {
        try {
            // get user
            Optional<Users> user = userRepository.findByEmail(email);
            List<ReservedTicket> bookedevents = reservedTicketsRepository.findByUserAndStatus(user.get(),
                    TicketStatus.RESERVED.name());
            if (bookedevents.isEmpty()) {
                return new ResponseDTO(ResponseCode.NOTFOUND, "No record found", null);
            }
            return new ResponseDTO(ResponseCode.OK, "event list", bookedevents);
        } catch (Exception e) {
            return new ResponseDTO(ResponseCode.SYSTEM_MALFUNCTION, e.getMessage(), null);
        }
    }

    public ResponseDTO cancelReservedTickets(long ticketId) {
        try {
            // check for reserved ticket with id
            Optional<ReservedTicket> bookedevents = reservedTicketsRepository.findByIdAndStatus(ticketId,
                     TicketStatus.RESERVED.name());
            if (bookedevents.isEmpty()) {
                return new ResponseDTO(ResponseCode.NOTFOUND, "No booked ticket found", null);
            }
            bookedevents.get().setStatus(TicketStatus.CANCEL);
            return new ResponseDTO(ResponseCode.OK, "event list", bookedevents.get());
        } catch (Exception e) {
            return new ResponseDTO(ResponseCode.SYSTEM_MALFUNCTION, e.getMessage(), null);
        }
    }

}
