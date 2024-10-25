import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.Subject;
import com.example.cab302project.model.AssignmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Assignment} class.
 * These tests cover methods related to assignment attributes such as name, description, grade, and status.
 */
public class AssignmentTest {
    private Assignment assignment;
    private Subject subject;

    /**
     * Initializes a {@link Subject} and an {@link Assignment} object before each test.
     * The subject is set as a part of assignment initialization.
     */
    @BeforeEach
    public void setUp() {
        subject = new Subject(1, "CAB501", "Intro to Programming", "Learn basic programming concepts");
        assignment = new Assignment("Assignment 1", "Solve pnp problem", "john_doe", subject, "30/9/2024");
    }

    /**
     * Tests the {@link Assignment#getName()} method.
     * Ensures the assignment name is correctly retrieved.
     */
    @Test
    public void testGetName() {
        assertEquals("Assignment 1", assignment.getName());
    }

    /**
     * Tests the {@link Assignment#setName(String)} method.
     * Ensures the assignment name is correctly updated.
     */
    @Test
    public void testSetName() {
        assignment.setName("Assignment 2");
        assertEquals("Assignment 2", assignment.getName());
    }

    /**
     * Tests the {@link Assignment#getDescription()} method.
     * Ensures the assignment description is correctly retrieved.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Solve pnp problem", assignment.getDescription());
    }

    /**
     * Tests the {@link Assignment#setDescription(String)} method.
     * Ensures the assignment description is correctly updated.
     */
    @Test
    public void testSetDescription() {
        assignment.setDescription("Complete all exercises");
        assertEquals("Complete all exercises", assignment.getDescription());
    }

    /**
     * Tests the {@link Assignment#getGrade()} method.
     * Ensures the grade is initially set to 0.
     */
    @Test
    public void testGetGrade() {
        assertEquals(0, assignment.getGrade());
    }

    /**
     * Tests the {@link Assignment#setGrade(float)} method.
     * Ensures the grade is correctly updated.
     */
    @Test
    public void testSetGrade() {
        assignment.setGrade(6.5f);
        assertEquals(6.5f, assignment.getGrade());
    }

    /**
     * Tests the {@link Assignment#getUsername()} method.
     * Ensures the username associated with the assignment is correctly retrieved.
     */
    @Test
    public void testGetUsername() {
        assertEquals("john_doe", assignment.getUsername());
    }

    /**
     * Tests the {@link Assignment#setUsername(String)} method.
     * Ensures the username associated with the assignment is correctly updated.
     */
    @Test
    public void testSetUsername() {
        assignment.setUsername("jane_doe");
        assertEquals("jane_doe", assignment.getUsername());
    }

    /**
     * Tests the {@link Assignment#getSubject()} method.
     * Ensures the subject associated with the assignment is correctly retrieved.
     */
    @Test
    public void testGetSubject() {
        assertEquals(subject, assignment.getSubject());
    }

    /**
     * Tests the {@link Assignment#setSubject(Subject)} method.
     * Ensures the subject associated with the assignment is correctly updated.
     */
    @Test
    public void testSetSubject() {
        Subject newSubject = new Subject(2, "CAB502", "Advanced Programming", "Learn advanced programming concepts");
        assignment.setSubject(newSubject);
        assertEquals(newSubject, assignment.getSubject());
    }

    /**
     * Tests the {@link Assignment#getDueDate()} method.
     * Ensures the assignment's due date is correctly retrieved.
     */
    @Test
    public void testGetDueDate() {
        assertEquals("30/9/2024", assignment.getDueDate());
    }

    /**
     * Tests the {@link Assignment#setDueDate(String)} method.
     * Ensures the assignment's due date is correctly updated.
     */
    @Test
    public void testSetDueDate() {
        assignment.setDueDate("30/11/2024");
        assertEquals("30/11/2024", assignment.getDueDate());
    }

    /**
     * Tests the {@link Assignment#getStatus()} method.
     * Ensures that the status is initially null before any update.
     */
    @Test
    public void testGetStatus() {
        assertNull(assignment.getStatus());
    }

    /**
     * Tests the {@link Assignment#setStatus(AssignmentStatus)} method.
     * Ensures the status is correctly updated with various {@link AssignmentStatus} values.
     */
    @Test
    public void testSetStatus() {
        assignment.setStatus(AssignmentStatus.URGENT);
        assertEquals(AssignmentStatus.URGENT, assignment.getStatus());

        assignment.setStatus(AssignmentStatus.IN_PROGRESS);
        assertEquals(AssignmentStatus.IN_PROGRESS, assignment.getStatus());

        assignment.setStatus(AssignmentStatus.OVERDUE);
        assertEquals(AssignmentStatus.OVERDUE, assignment.getStatus());

        assignment.setStatus(AssignmentStatus.PENDING);
        assertEquals(AssignmentStatus.PENDING, assignment.getStatus());
    }
}
