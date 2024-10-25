import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.MockAssignmentDAO;
import com.example.cab302project.model.AssignmentManager;
import com.example.cab302project.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link AssignmentManager} class.
 * These tests cover various methods such as adding, updating, deleting, and searching assignments.
 */
public class AssignmentManagerTest {
    private AssignmentManager assignmentManager;

    /**
     * Initializes the {@link AssignmentManager} instance before each test case.
     * Uses a mock implementation of {@link MockAssignmentDAO}.
     */
    @BeforeEach
    public void setUp() {
        assignmentManager = new AssignmentManager(new MockAssignmentDAO());
    }

    /**
     * Tests the addition of an assignment to the {@link AssignmentManager}.
     * Ensures the assignment is successfully added and retrieved.
     */
    @Test
    public void testAddAssignment() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment = new Assignment("AT 1", "Description", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assertEquals(1, assignmentManager.getAllAssignments("john_doe").size());
        assertEquals(assignment, assignmentManager.getAllAssignments("john_doe").get(0));
    }

    /**
     * Tests the update functionality of the {@link AssignmentManager}.
     * Ensures that an assignment's description is successfully updated.
     */
    @Test
    public void testUpdateAssignment() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment = new Assignment("AT3", "Description", "Ryan Pam", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assignment.setDescription("Updated Description");
        assignmentManager.updateAssignment(assignment);
        Assignment updatedAssignment = assignmentManager.getAssignment(0);
        assertEquals("Updated Description", updatedAssignment.getDescription());
    }

    /**
     * Tests the deletion of an assignment from the {@link AssignmentManager}.
     * Ensures the assignment is removed and no longer retrievable.
     */
    @Test
    public void testDeleteAssignment() {
        Assignment assignment = new Assignment("AT2", "Description", "Ryan Pam", null, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assertEquals(1, assignmentManager.getAllAssignments("Ryan Pam").size());
        assignmentManager.deleteAssignment(assignment);
        assertEquals(0, assignmentManager.getAllAssignments("Ryan Pam").size());
    }

    /**
     * Tests retrieving a specific assignment by its ID.
     * Ensures the correct assignment is returned from the {@link AssignmentManager}.
     */
    @Test
    public void testGetAssignment() {
        Assignment assignment = new Assignment("AT2", "Description", "john_doe", null, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        Assignment retrievedAssignment = assignmentManager.getAssignment(0);
        assertEquals(assignment, retrievedAssignment);
    }

    /**
     * Tests retrieving all assignments for a specific user.
     * Verifies the correct number of assignments are returned.
     */
    @Test
    public void testGetAllAssignments() {
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", null, "30/6/2025");
        Assignment assignment2 = new Assignment("AT2", "Description 2", "john_doe", null, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> assignments = assignmentManager.getAllAssignments("john_doe");
        assertEquals(2, assignments.size());
    }

    /**
     * Tests searching assignments by name using a full query.
     * Ensures the correct assignment is returned when the query matches exactly.
     */
    @Test
    public void testSearchAssignments() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", subject, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Description 2", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("AT1");
        assertEquals(1, searchResults.size());
        assertEquals(assignment1, searchResults.get(0));
    }

    /**
     * Tests searching assignments with an empty query string.
     * Ensures all assignments are returned.
     */
    @Test
    public void testSearchAssignmentsEmptyQuery() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", subject, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Description 2", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("");
        assertEquals(2, searchResults.size());
    }

    /**
     * Tests case-insensitive search functionality in {@link AssignmentManager}.
     * Verifies that the search works regardless of the case of the query.
     */
    @Test
    public void testSearchAssignmentsCaseInsensitive() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", subject, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Description 2", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("at1");
        assertEquals(1, searchResults.size());
        assertEquals(assignment1, searchResults.get(0));
    }

    /**
     * Tests searching assignments by a partial query.
     * Ensures the search returns assignments matching part of the query.
     */
    @Test
    public void testSearchAssignmentsPartialName() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", subject, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Description 2", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("Project");
        assertEquals(1, searchResults.size());
        assertEquals(assignment2, searchResults.get(0));
    }

    /**
     * Tests searching assignments by subject.
     * Ensures the search returns all assignments matching the subject code.
     */
    @Test
    public void testSearchAssignmentsBySubject() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", subject, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Description 2", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("CS101");
        assertEquals(2, searchResults.size());
    }

    /**
     * Tests searching for assignments with a query that has no matches.
     * Ensures that no results are returned.
     */
    @Test
    public void testSearchAssignmentsNoResults() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", subject, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Description 2", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("Nonexistent");
        assertEquals(0, searchResults.size());
    }

    /**
     * Tests searching for assignments by their description.
     * Ensures that assignments are returned when the description matches the query.
     */
    @Test
    public void testSearchAssignmentsByDescription() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment1 = new Assignment("AT1", "First assignment", "john_doe", subject, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Final project", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("First assignment");
        assertEquals(1, searchResults.size());
        assertEquals(assignment1, searchResults.get(0));
    }
}
