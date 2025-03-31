/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.workaround.librarymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Olawale
 */
@SpringBootApplication
@EnableScheduling
public class Librarymanagement {

    public static void main(String[] args) {
        SpringApplication.run(Librarymanagement.class, args);
    }
}
