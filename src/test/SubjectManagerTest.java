import com.example.cab302project.model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


// Class for testing subject manager
public class SubjectManagerTest {

    private SubjectManager subjectManager;

    // Initialise with mock data
    private Subject[] subjects = {
            new Subject(1, "CS101", "Computer Science", "Introduction to Computer Science"),
            new Subject(1, "CS102", "Mathematics", "Introduction to Mathematics"),
            new Subject(2, "CS201", "Data Structures", "Advanced Data Structures"),
            new Subject(2, "CS202", "Algorithms", "Introduction to Algorithms"),
            new Subject(1, "CS301", "Operating Systems", "Introduction to Operating Systems")
    };

    // Initialise a SubjectManager object
    @BeforeEach
    public void setUp() {
        subjectManager = new SubjectManager(new MockSubjectDAO());
    }

    @Test
    public void testAddSubject() {
        subjectManager.addSubject(subjects[0]);
        List<Subject> allSubjects = subjectManager.getAllSubjects();
        assertEquals(1, allSubjects.size());
        assertEquals(subjects[0], allSubjects.get(0));
    }

    @Test
    public void testUpdateSubject() {
        subjectManager.addSubject(subjects[0]);
        subjects[0].setName("Advanced Computer Science");
        subjectManager.updateSubject(subjects[0]);

        Subject updatedSubject = subjectManager.getSubject(subjects[0].getId());
        assertEquals("Advanced Computer Science", updatedSubject.getName());
    }

    @Test
    public void testDeleteSubject() {
        subjectManager.addSubject(subjects[0]);
        subjectManager.deleteSubject(subjects[0]);
        List<Subject> allSubjects = subjectManager.getAllSubjects();
        assertEquals(0, allSubjects.size());
    }

    @Test
    public void testGetSubject() {
        subjectManager.addSubject(subjects[0]);
        Subject fetchedSubject = subjectManager.getSubject(subjects[0].getId());
        assertEquals(subjects[0], fetchedSubject);
    }

    @Test
    public void testGetAllSubjects() {
        for (Subject subject : subjects) {
            subjectManager.addSubject(subject);
        }
        List<Subject> allSubjects = subjectManager.getAllSubjects();
        assertEquals(5, allSubjects.size());
        for (Subject subject : subjects) {
            assertTrue(allSubjects.contains(subject));
        }
    }

    @Test
    public void testSearchSubjectByCode() {
        subjectManager.addSubject(subjects[0]);
        subjectManager.addSubject(subjects[1]);
        List<Subject> foundSubjects = subjectManager.searchSubjects("CS101");
        assertEquals(1, foundSubjects.size());
        assertEquals(subjects[0], foundSubjects.get(0));
    }

    @Test
    public void testSearchNoResults() {
        for (Subject subject : subjects) {
            subjectManager.addSubject(subject);
        }
        List<Subject> foundSubjects = subjectManager.searchSubjects("Physics");
        assertEquals(0, foundSubjects.size());
    }

    @Test
    public void testSearchEmptyQuery() {
        for (Subject subject : subjects) {
            subjectManager.addSubject(subject);
        }
        List<Subject> foundSubjects = subjectManager.searchSubjects("");
        assertEquals(5, foundSubjects.size());
    }

    @Test
    public void testSearchNullQuery() {
        for (Subject subject : subjects) {
            subjectManager.addSubject(subject);
        }
        List<Subject> foundSubjects = subjectManager.searchSubjects(null);
        assertEquals(5, foundSubjects.size());
    }
}
