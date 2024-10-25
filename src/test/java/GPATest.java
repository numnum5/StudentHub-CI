import com.example.cab302project.model.GPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link GPA} class.
 * These tests cover the GPA model's functionality such as setting and retrieving GPA values and usernames.
 */
class GPATest {
    private GPA gpa;

    /**
     * Initializes a {@link GPA} object before each test.
     * A default username and GPA value are set for testing.
     */
    @BeforeEach
    void setUp() {
        gpa = new GPA("testUser", 3.5);
    }

    /**
     * Tests the constructor and the {@link GPA#getUsername()} and {@link GPA#getGpa()} methods.
     * Ensures the constructor correctly initializes the GPA object with a username and GPA value.
     */
    @Test
    void testConstructorAndGetters() {
        assertEquals("testUser", gpa.getUsername());
        assertEquals(3.5, gpa.getGpa());
    }

    /**
     * Tests the {@link GPA#setGpa(double)} and {@link GPA#getGpa()} methods.
     * Ensures the GPA value is correctly set and retrieved.
     */
    @Test
    void testGetGPA() {
        gpa.setGpa(6.5);
        assertEquals(6.5, gpa.getGpa());
    }

    /**
     * Tests the {@link GPA#setUsername(String)} and {@link GPA#getUsername()} methods.
     * Ensures the username is correctly set and retrieved.
     */
    @Test
    void testGetUsername() {
        gpa.setUsername("Ryan Pam");
        assertEquals("Ryan Pam", gpa.getUsername());
    }

    /**
     * Tests the {@link GPA#setGpa(double)} method.
     * Ensures the GPA value is correctly updated.
     */
    @Test
    void testSetGPA() {
        gpa.setGpa(3.8);
        assertEquals(3.8, gpa.getGpa());
    }

    /**
     * Tests the {@link GPA#setUsername(String)} method.
     * Ensures the username is correctly updated.
     */
    @Test
    void testSetUsername() {
        gpa.setUsername("newUser");
        assertEquals("newUser", gpa.getUsername());
    }
}
