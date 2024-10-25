import com.example.cab302project.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link User} class.
 * This class tests various functionalities of the User model such as setting and retrieving user details.
 */
public class UserTest {

    private User user;

    /**
     * Initializes a {@link User} object before each test.
     * The user object is set up with default values for first name, last name, username, and password.
     */
    @BeforeEach
    public void setUp() {
        user = new User("John", "Doe", "johndoe", "password123");
    }

    /**
     * Tests the {@link User} constructor and the corresponding getter methods.
     * Ensures that the user's first name, last name, username, and password are correctly set through the constructor.
     */
    @Test
    public void testConstructor() {
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    /**
     * Tests the {@link User#setFirstName(String)} and {@link User#getFirstName()} methods.
     * Ensures that the first name can be updated and retrieved correctly.
     */
    @Test
    public void testSetAndGetFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    /**
     * Tests the {@link User#setLastName(String)} and {@link User#getLastName()} methods.
     * Verifies that the last name can be updated and retrieved correctly.
     */
    @Test
    public void testSetAndGetLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    /**
     * Tests the {@link User#setUsername(String)} and {@link User#getUsername()} methods.
     * Ensures that the username can be updated and retrieved correctly.
     */
    @Test
    public void testSetAndGetUsername() {
        user.setUsername("janesmith");
        assertEquals("janesmith", user.getUsername());
    }

    /**
     * Tests the {@link User#setPassword(String)} and {@link User#getPassword()} methods.
     * Verifies that the password can be updated and retrieved correctly.
     */
    @Test
    public void testSetAndGetPassword() {
        user.setPassword("newpassword456");
        assertEquals("newpassword456", user.getPassword());
    }

    /**
     * Tests the {@link User#setId(int)} and {@link User#getId()} methods.
     * Ensures that the user ID can be set and retrieved correctly.
     */
    @Test
    public void testSetAndGetId() {
        user.setId(1001);
        assertEquals(1001, user.getId());
    }

    /**
     * Tests the {@link User#getFullName()} method.
     * Verifies that the full name is correctly formed by concatenating the first name and last name.
     */
    @Test
    public void testGetFullName() {
        assertEquals("John Doe", user.getFullName());
        user.setFirstName("Jane");
        user.setLastName("Smith");
        assertEquals("Jane Smith", user.getFullName());
    }
}
