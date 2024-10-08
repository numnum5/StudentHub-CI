import com.example.cab302project.model.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for testing Subject class
 */
public class SubjectTest {

    private Subject subject;

    @BeforeEach
    public void setUp() {
        subject = new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science");
    }

    @Test
    public void testGetId() {
        subject.setId(10);
        assertEquals(10, subject.getId());
    }

    @Test
    public void testSetId() {
        subject.setId(20);
        assertEquals(20, subject.getId());
    }

    @Test
    public void testGetUnitCode() {
        assertEquals("CS101", subject.getUnitCode());
    }

    @Test
    public void testSetUnitCode() {
        subject.setUnitCode("CS102");
        assertEquals("CS102", subject.getUnitCode());
    }

    @Test
    public void testGetName() {
        assertEquals("Computer Science", subject.getName());
    }

    @Test
    public void testSetName() {
        subject.setName("Advanced Computer Science");
        assertEquals("Advanced Computer Science", subject.getName());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Introduction to Computer Science", subject.getDescription());
    }

    @Test
    public void testSetDescription() {
        subject.setDescription("Deep dive into computer science concepts");
        assertEquals("Deep dive into computer science concepts", subject.getDescription());
    }

    @Test
    public void testGetSemester() {
        assertEquals(1, subject.getSemester());
    }

    @Test
    public void testSetSemester() {
        subject.setSemester(2);
        assertEquals(2, subject.getSemester());
    }
}
