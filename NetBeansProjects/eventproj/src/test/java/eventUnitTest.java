/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.workaround.eventproj.DTO.Credential;
import com.workaround.eventproj.DTO.EventRequestDTO;
import com.workaround.eventproj.DTO.User;
import com.workaround.eventproj.constant.Category;
import com.workaround.eventproj.contoller.UserController;
import com.workaround.eventproj.service.EventService;
import com.workaround.eventproj.service.UserService;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Olawale
 */
@ContextConfiguration
public class eventUnitTest {
      private UserService userService;
      private EventService eventService;
      private UserController userController;
    
    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
   
    
     @Test
    @DisplayName("Create a user")
    void createValidUser() {

        User createUser = new User();
        createUser.setEmail("kdb@gmail.com");
        createUser.setName("Kevin DeBruyne");
        createUser.setPassword("Password1");

         assertDoesNotThrow(() -> {
            userService.createUser(createUser);
        });
    }
    
    @Test
    @DisplayName("Authenticate user")
    void createValidLogin() {

        Credential auth = new Credential();
        auth.setEmail("kdb@gmail.com");
        auth.setPassword("Password1");

         assertDoesNotThrow(() -> {
            userController.authenticateAndGetToken(auth);
        });
    }
    
    
      @Test
    @DisplayName("Create an event")
    void createValidEvent() {

        EventRequestDTO createEvent = new EventRequestDTO();
        createEvent.setCategory(Category.GAME);
        createEvent.setName("Premier league Mancity vs Liverpool");
        createEvent.setDescription("mancity vs liverpool second leg game");
        createEvent.setAvailableAttendeesCount(500);
        createEvent.setDate(LocalDate.now());

         assertDoesNotThrow(() -> {
            eventService.createEvent(createEvent);
        });
    }
    
    
}
