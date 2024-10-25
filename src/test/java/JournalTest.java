import com.example.cab302project.model.Journal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Journal} class.
 * This class tests various aspects of the Journal model such as setting and retrieving
 * journal entry details including the mood, username, and ID.
 */
class JournalTest {
    private Journal journal;

    /**
     * Initializes a {@link Journal} object before each test.
     * The journal object is set up with a default entry, mood, and username.
     */
    @BeforeEach
    void setUp() {
        journal = new Journal("This is a test entry.", "Happy", "testUser");
    }

    /**
     * Tests the {@link Journal} constructor and the corresponding getter methods.
     * Ensures that the journal entry, mood, username, and default ID are correctly set through the constructor.
     */
    @Test
    void testConstructorAndGetters() {
        assertEquals("This is a test entry.", journal.getEntry());
        assertEquals("Happy", journal.getMood());
        assertEquals("testUser", journal.getUsername());
        assertEquals(0, journal.getId());  // Default ID
    }

    /**
     * Tests the setter methods of the {@link Journal} class.
     * Ensures that the journal entry, mood, username, and ID can be updated and retrieved correctly.
     */
    @Test
    void testSetters() {
        journal.setEntry("Updated entry.");
        journal.setMood("Sad");
        journal.setUsername("newUser");
        journal.setId(1);

        assertEquals("Updated entry.", journal.getEntry());
        assertEquals("Sad", journal.getMood());
        assertEquals("newUser", journal.getUsername());
        assertEquals(1, journal.getId());
    }

    /**
     * Tests the default ID of a new {@link Journal} object.
     * Ensures that a new journal's ID defaults to 0 upon initialization.
     */
    @Test
    void testDefaultId() {
        assertEquals(0, journal.getId());
    }
}
