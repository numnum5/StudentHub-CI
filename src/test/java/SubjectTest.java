import com.example.cab302project.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Subject} class.
 * This class tests the functionalities of the Subject model, such as setting and getting various attributes.
 */
public class SubjectTest {

    private Subject subject;

    /**
     * Initializes a {@link Subject} object before each test.
     * A default subject is set for testing with unit code, name, and description.
     */
    @BeforeEach
    public void setUp() {
        subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
    }

    /**
     * Tests the {@link Subject#getId()} and {@link Subject#setId(int)} methods.
     * Ensures that the ID can be set and retrieved correctly.
     */
    @Test
    public void testGetId() {
        subject.setId(10);
        assertEquals(10, subject.getId());
    }

    /**
     * Tests the {@link Subject#setId(int)} method.
     * Verifies that the subject ID is updated correctly.
     */
    @Test
    public void testSetId() {
        subject.setId(20);
        assertEquals(20, subject.getId());
    }

    /**
     * Tests the {@link Subject#getUnitCode()} method.
     * Ensures that the unit code is retrieved correctly.
     */
    @Test
    public void testGetUnitCode() {
        assertEquals("CS101", subject.getUnitCode());
    }

    /**
     * Tests the {@link Subject#setUnitCode(String)} method.
     * Verifies that the unit code is updated correctly.
     */
    @Test
    public void testSetUnitCode() {
        subject.setUnitCode("CS102");
        assertEquals("CS102", subject.getUnitCode());
    }

    /**
     * Tests the {@link Subject#getName()} method.
     * Ensures that the subject name is retrieved correctly.
     */
    @Test
    public void testGetName() {
        assertEquals("Computer Science", subject.getName());
    }

    /**
     * Tests the {@link Subject#setName(String)} method.
     * Verifies that the subject name is updated correctly.
     */
    @Test
    public void testSetName() {
        subject.setName("Advanced Computer Science");
        assertEquals("Advanced Computer Science", subject.getName());
    }

    /**
     * Tests the {@link Subject#getDescription()} method.
     * Ensures that the description is retrieved correctly.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Introduction to Computer Science", subject.getDescription());
    }

    /**
     * Tests the {@link Subject#setDescription(String)} method.
     * Verifies that the subject description is updated correctly.
     */
    @Test
    public void testSetDescription() {
        subject.setDescription("Deep dive into computer science concepts");
        assertEquals("Deep dive into computer science concepts", subject.getDescription());
    }

    /**
     * Tests the {@link Subject#getSemester()} method.
     * Ensures that the semester is retrieved correctly.
     */
    @Test
    public void testGetSemester() {
        assertEquals(1, subject.getSemester());
    }

    /**
     * Tests the {@link Subject#setSemester(int)} method.
     * Verifies that the semester is updated correctly.
     */
    @Test
    public void testSetSemester() {
        subject.setSemester(2);
        assertEquals(2, subject.getSemester());
    }
}
