
import com.workaround.librarymanagement.DTO.BookDTO;
import com.workaround.librarymanagement.Librarymanagement;
import com.workaround.librarymanagement.model.Book;
import com.workaround.librarymanagement.service.BookService;
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
public class bookingTestUnit {

    @Autowired
    private BookService bookService;

    @Test
    public void testBooksRecord() {
        ResponseEntity<?> res = bookService.fetchAllBooks(0, 10);

        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
        assertNotNull(res.getBody());
    }

    @Test
    public void testBooksById() {
        ResponseEntity<?> res = bookService.fetchBookById(1);
        System.out.println(res.getBody());
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
    }

    @Test
    public void addBookValidRequestTest() {
        BookDTO request = new BookDTO();
        request.setAuthor("Test Author");
        request.setIsbn("9799776476545");
        request.setPublicationYear("2024");
        request.setTitle("Test Book");

        ResponseEntity<?> res = bookService.createBook(request, "System");

        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertNotNull(res.getBody());
        System.out.println(res.getBody());
    }

    @Test
    public void addBookInValidRequestTest() {
        //validating required fields
        BookDTO request = new BookDTO();
        request.setAuthor("Test Author");
        request.setIsbn("977899776476545");
        request.setPublicationYear("2024");
        request.setTitle("Test Book");

        ResponseEntity<?> res = bookService.createBook(request, "System");
        System.out.println(res.getBody() + " " + res.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
        assertNotNull(res.getBody());
    }
}
