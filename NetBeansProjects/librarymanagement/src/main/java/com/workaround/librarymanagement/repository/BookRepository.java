/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.librarymanagement.repository;

import com.workaround.librarymanagement.model.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */
public interface BookRepository extends JpaRepository<Book, Long>{
    
    Optional<Book> findByTitle(String title);
}
