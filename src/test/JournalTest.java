import com.example.cab302project.model.Journal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing journal model
 */
class JournalTest {
    private Journal journal;

    @BeforeEach
    void setUp() {
        journal = new Journal("This is a test entry.", "Happy", "testUser");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("This is a test entry.", journal.getEntry());
        assertEquals("Happy", journal.getMood());
        assertEquals("testUser", journal.getUsername());
        assertEquals(0, journal.getId());
    }

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

    @Test
    void testDefaultId() {
        assertEquals(0, journal.getId());
    }
}
