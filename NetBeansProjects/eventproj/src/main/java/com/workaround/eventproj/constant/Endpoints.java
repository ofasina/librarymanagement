/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.eventproj.constant;

/**
 *
 * @author Olawale
 */
public class Endpoints {
    
    // Authentication
    public static final String VALIDATE_USER = "/auth";

    // Users
    public static final String CREATE_USER = "/users";

    // Events
    public static final String CREATE_EVENTS = "/events";
    public static final String GET_EVENTS = "/events";
    public static final String RESERVE_TICKETS_EVENTS = "/events/{eventId}/tickets";
    public static final String VIEW_BOOKED_EVENTS = "/events/view/{email}";
    public static final String CANCEL_BOOKED_EVENTS = "/events/cancel/{ticketId}";
    
}
