import com.example.cab302project.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the User class
 */
public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize a User object before each test
        user = new User("John", "Doe", "johndoe", "password123");
    }

    @Test
    public void testConstructor() {
        // Test if the constructor correctly assigns values
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testSetAndGetFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testSetAndGetUsername() {
        user.setUsername("janesmith");
        assertEquals("janesmith", user.getUsername());
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }

    @Test
    public void testSetAndGetId() {
        user.setId(1001);
        assertEquals(1001, user.getId());
    }

    @Test
    public void testGetFullName() {
        assertEquals("John Doe", user.getFullName());
        user.setFirstName("Jane");
        user.setLastName("Smith");
        assertEquals("Jane Smith", user.getFullName());
    }
}
