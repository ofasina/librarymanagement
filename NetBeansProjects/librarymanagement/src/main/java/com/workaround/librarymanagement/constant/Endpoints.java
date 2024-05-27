/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.workaround.librarymanagement.constant;

/**
 *
 * @author Olawale
 */
public class Endpoints {

    // authentication management
    public static final String VALIDATE_USER = "/api/user-auth";
    public static final String CREATE_USER = "/api/user_management/create";

    // book management
    public static final String CREATE_BOOK = "/api/books";
    public static final String DELETE_BOOK = "/api/books/{id}";
    public static final String UPDATE_BOOK = "/api/books/{id}";
    public static final String ALL_BOOKS = "/api/books";
    public static final String BOOK_ID = "/api/books/{id}";
    
    //patron management
      public static final String CREATE_PATRON = "/api/patrons";
    public static final String DELETE_PATRON = "/api/patrons/{id}";
    public static final String UPDATE_PATRON = "/api/patrons/{id}";
    public static final String ALL_PATRONS = "/api/patrons";
    public static final String PATRON_ID = "/api/patrons/{id}";
    
    //borrowing manaagement
    public static final String BORROW_BOOK ="/api/borrow/{bookId}/patron/{patronId}";
    public static final String RETURN_BOOK ="/api/return/{bookId}/patron/{patronId}";
}
