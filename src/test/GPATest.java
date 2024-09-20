import com.example.cab302project.model.GPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GPATest {
    private GPA gpa;

    @BeforeEach
    void setUp() {
        gpa = new GPA("testUser", 3.5);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("testUser", gpa.getUsername());
        assertEquals(3.5, gpa.getGpa());
    }

    @Test
    void testGetGPA(){
        gpa.setGpa(6.5);
        assertEquals(6.5, gpa.getGpa());
    }

    @Test
    void testGetUsername(){
        gpa.setUsername("Ryan Pam");
        assertEquals("Ryan Pam", gpa.getUsername());
    }

    @Test
    void testSetGPA() {
        gpa.setGpa(3.8);
        assertEquals(3.8, gpa.getGpa());
    }
    @Test
    void testSetUsername() {
        gpa.setUsername("newUser");
        assertEquals("newUser", gpa.getUsername());
    }
}
