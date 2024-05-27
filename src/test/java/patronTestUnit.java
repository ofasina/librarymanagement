
import com.workaround.librarymanagement.DTO.BookDTO;
import com.workaround.librarymanagement.DTO.PatronDTO;
import com.workaround.librarymanagement.Librarymanagement;
import com.workaround.librarymanagement.model.Book;
import com.workaround.librarymanagement.service.BookService;
import com.workaround.librarymanagement.service.PatronService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Olawale
 */
@SpringBootTest(classes = Librarymanagement.class)
@RequiredArgsConstructor
public class patronTestUnit {

    @Autowired
    private PatronService patronService;

    @Test
    public void testPatronsRecord() {
        ResponseEntity<?> res = patronService.fetchAllPatrons(0, 10);

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
        assertNotNull(res.getBody());
    }

    @Test
    public void testPatronsById() {
        ResponseEntity<?> res = patronService.fetchPatronById(1);
        System.out.println(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
    }

    @Test
    public void addBookValidRequestTest() {
        PatronDTO request = new PatronDTO();
        request.setAddress("Test street");
        request.setEmail("test@gmail.com");
        request.setMobileNumber("07010671015");
        request.setName("Test Env");

        ResponseEntity<?> res = patronService.createPatron(request, "System");

        assertEquals(HttpStatus.CREATED, res.getStatusCode());
        assertNotNull(res.getBody());
        System.out.println(res.getBody());
    }

    @Test
    public void addBookInValidRequestTest() {
        //validating required fields
         PatronDTO request = new PatronDTO();
        request.setAddress("Test street");
        request.setEmail("test@gmail.com");
        request.setMobileNumber("07A1067101586");
        request.setName("Test Env");

        ResponseEntity<?> res = patronService.createPatron(request, "System");

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
        System.out.println(res.getBody());
    }
}
