import com.example.cab302project.model.Assignment;
import com.example.cab302project.model.Subject;
import com.example.cab302project.model.AssignmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {

    private Subject subject;

    @BeforeEach
    public void setUp() {
        // Initialize a new Subject object before each test
        subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
    }

    @Test
    public void testGetId() {
        // Test the ID getter
        subject.setId(10);
        assertEquals(10, subject.getId());
    }

    @Test
    public void testSetId() {
        // Test the ID setter
        subject.setId(20);
        assertEquals(20, subject.getId());
    }

    @Test
    public void testGetUnitCode() {
        // Test the unit code getter
        assertEquals("CS101", subject.getUnitCode());
    }

    @Test
    public void testSetUnitCode() {
        // Test the unit code setter
        subject.setUnitCode("CS102");
        assertEquals("CS102", subject.getUnitCode());
    }

    @Test
    public void testGetName() {
        // Test the name getter
        assertEquals("Computer Science", subject.getName());
    }

    @Test
    public void testSetName() {
        // Test the name setter
        subject.setName("Advanced Computer Science");
        assertEquals("Advanced Computer Science", subject.getName());
    }

    @Test
    public void testGetDescription() {
        // Test the description getter
        assertEquals("Introduction to Computer Science", subject.getDescription());
    }

    @Test
    public void testSetDescription() {
        // Test the description setter
        subject.setDescription("Deep dive into computer science concepts");
        assertEquals("Deep dive into computer science concepts", subject.getDescription());
    }

    @Test
    public void testGetSemester() {
        // Test the semester getter
        assertEquals(1, subject.getSemester());
    }

    @Test
    public void testSetSemester() {
        // Test the semester setter
        subject.setSemester(2);
        assertEquals(2, subject.getSemester());
    }

    @Test
    public void testToString() {
        // Test the toString method
        String expected = "Subject{id=0, unitCode='CS101', name='Computer Science', description='Introduction to Computer Science'}";
        assertEquals(expected, subject.toString());
    }
}
