import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.Subject;
import com.example.cab302project.model.AssignmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// Class for testing Assignment object
public class AssignmentTest {
    private Assignment assignment;
    private Subject subject;

    // Initialise a Subject object and Assignment object for subsequent testing
    @BeforeEach
    public void setUp() {
        subject = new Subject(1, "CAB501", "Intro to Programming", "Learn basic programming concepts");
        assignment = new Assignment("Assignment 1", "Solve pnp problem", "john_doe", subject, "30/9/2024");
    }

    @Test
    public void testGetName() {
        assertEquals("Assignment 1", assignment.getName());
    }

    @Test
    public void testSetName() {
        assignment.setName("Assignment 2");
        assertEquals("Assignment 2", assignment.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Solve pnp problem", assignment.getDescription());
    }

    @Test
    public void testSetDescription() {
        assignment.setDescription("Complete all exercises");
        assertEquals("Complete all exercises", assignment.getDescription());
    }

    @Test
    public void testGetGrade() {
        assertEquals(0, assignment.getGrade());
    }

    @Test
    public void testSetGrade() {
        assignment.setGrade(6.5f);
        assertEquals(6.5f, assignment.getGrade());
    }

    @Test
    public void testGetUsername() {
        assertEquals("john_doe", assignment.getUsername());
    }

    @Test
    public void testSetUsername() {
        assignment.setUsername("jane_doe");
        assertEquals("jane_doe", assignment.getUsername());
    }

    @Test
    public void testGetSubject() {
        assertEquals(subject, assignment.getSubject());
    }

    @Test
    public void testSetSubject() {
        Subject newSubject = new Subject(2, "CAB502", "Advanced Programming", "Learn advanced programming concepts");
        assignment.setSubject(newSubject);
        assertEquals(newSubject, assignment.getSubject());
    }

    @Test
    public void testGetDueDate() {
        assertEquals("30/9/2024", assignment.getDueDate());
    }

    @Test
    public void testSetDueDate() {
        assignment.setDueDate("30/11/2024");
        assertEquals("30/11/2024", assignment.getDueDate());
    }

    @Test
    public void testGetStatus() {
        assertNull(assignment.getStatus());
    }

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
