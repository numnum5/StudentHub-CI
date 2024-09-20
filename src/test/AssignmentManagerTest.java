import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.MockAssignmentDAO;
import com.example.cab302project.model.AssignmentManager;
import com.example.cab302project.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Class for testing AssignmentMananger with assignment data access object.
public class AssignmentManagerTest {
    private AssignmentManager assignmentManager;
    private MockAssignmentDAO mockAssignmentDAO;

    @BeforeEach
    public void setUp() {
        mockAssignmentDAO = new MockAssignmentDAO();
        assignmentManager = new AssignmentManager(mockAssignmentDAO);
    }

    @Test
    public void testAddAssignment() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment = new Assignment("AT 1", "Description", "john_doe", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assertEquals(1, mockAssignmentDAO.subjects.size());

        // Check if the get function with the first id matches the assignment object
        assertEquals(assignment, mockAssignmentDAO.subjects.get(0));
    }

    @Test
    public void testUpdateAssignment() {
        Subject subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
        Assignment assignment = new Assignment("AT3", "Description", "Ryan Pam", subject, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assignment.setDescription("Updated Description");
        assignmentManager.updateAssignment(assignment);
        Assignment updatedAssignment = mockAssignmentDAO.getAssignment(0);
        assertEquals("Updated Description", updatedAssignment.getDescription());
    }

    @Test
    public void testDeleteAssignment() {
        Assignment assignment = new Assignment("AT2", "Description", "Ryan Pam", null, "30/6/2025");
        assignmentManager.addAssignment(assignment);
        assertEquals(1, mockAssignmentDAO.subjects.size());
        assignmentManager.deleteAssignment(assignment);
        assertEquals(0, mockAssignmentDAO.subjects.size());
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
        Assignment assignment1 = new Assignment("AT1", "Description 1", "john_doe", null, "30/6/2025");
        Assignment assignment2 = new Assignment("Project 1", "Description 2", "john_doe", null, "30/6/2025");
        assignmentManager.addAssignment(assignment1);
        assignmentManager.addAssignment(assignment2);
        List<Assignment> searchResults = assignmentManager.searchAssignments("AT1");
        assertEquals(1, searchResults.size());
        assertEquals(assignment1, searchResults.get(0));
    }
}
