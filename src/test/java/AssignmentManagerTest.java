import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.MockAssignmentDAO;
import com.example.cab302project.model.AssignmentManager;
import com.example.cab302project.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing AssignmentManager which includes IAssignmentDAO functionalities
 */
public class AssignmentManagerTest {
    private AssignmentManager assignmentManager;

    @BeforeEach
    public void setUp() {
        assignmentManager = new AssignmentManager(new MockAssignmentDAO());
    }

    @Test
    public void testAddAssignment() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment = new Assignment("AT 1", "Description", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assertEquals(1, assignmentManager.getAllAssignments("john_doe").size());

        // Check if the get function with the first id matches the assignment object
        assertEquals(assignment, assignmentManager.getAllAssignments("john_doe").get(0));
    }

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

    @Test
    public void testDeleteAssignment() {
        Assignment assignment = new Assignment("AT2", "Description", "Ryan Pam", null, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assertEquals(1, assignmentManager.getAllAssignments("Ryan Pam").size());
        assignmentManager.deleteAssignment(assignment);
        assertEquals(0,  assignmentManager.getAllAssignments("Ryan Pam").size());
    }

    @Test
    public void testGetAssignment() {
        Assignment assignment = new Assignment("AT2", "Description", "john_doe", null, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        Assignment retrievedAssignment = assignmentManager.getAssignment(0);
        assertEquals(assignment, retrievedAssignment);
    }

    @Test
    public void testGetAllAssignments() {
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", null, "30/6/2025");
        Assignment assignment2 = new Assignment("AT2", "Description 2", "john_doe", null, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> assignments = assignmentManager.getAllAssignments("john_doe");
        assertEquals(2, assignments.size());
    }

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
