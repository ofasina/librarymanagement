/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.workaround.eventproj.repository;

import com.workaround.eventproj.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Olawale
 */

public interface UserRepository extends JpaRepository<Users, Long>{
    
      Optional<Users> findByName(String name);
    Optional<Users> findByEmail(String email);
    
}
