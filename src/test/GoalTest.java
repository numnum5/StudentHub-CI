package com.example.cab302project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MockGoalDAOTest {

    private MockGoalDAO mockGoalDAO;

    // Set up the mock DAO before each test
    @BeforeEach
    public void setUp() {
        mockGoalDAO = new MockGoalDAO();  // Reinitialize to get a clean state for each test
    }

    @Test
    public void testSaveGoal() {
        // Act
        mockGoalDAO.saveGoal("Learn Java", "Complete Java tutorial");

        // Assert
        List<String[]> goals = mockGoalDAO.getAllGoals();
        assertEquals(1, goals.size());
        assertEquals("Learn Java", goals.get(0)[0]);
        assertEquals("Complete Java tutorial", goals.get(0)[1]);
    }

    @Test
    public void testGetAllGoals() {
        // Arrange
        mockGoalDAO.saveGoal("Learn Java", "Complete Java tutorial");
        mockGoalDAO.saveGoal("Learn SQL", "Complete SQL tutorial");

        // Act
        List<String[]> goals = mockGoalDAO.getAllGoals();

        // Assert
        assertEquals(2, goals.size());
        assertEquals("Learn Java", goals.get(0)[0]);
        assertEquals("Complete Java tutorial", goals.get(0)[1]);
        assertEquals("Learn SQL", goals.get(1)[0]);
        assertEquals("Complete SQL tutorial", goals.get(1)[1]);
    }

    @Test
    public void testDeleteGoal() {
        // Arrange
        mockGoalDAO.saveGoal("Learn Java", "Complete Java tutorial");
        mockGoalDAO.saveGoal("Learn SQL", "Complete SQL tutorial");

        // Act
        mockGoalDAO.deleteGoal("Learn Java");

        // Assert
        List<String[]> goals = mockGoalDAO.getAllGoals();
        assertEquals(1, goals.size());  // Only one goal should remain
        assertEquals("Learn SQL", goals.get(0)[0]);
    }

    @Test
    public void testSearchGoals() {
        // Arrange
        mockGoalDAO.saveGoal("Learn Java", "Complete Java tutorial");
        mockGoalDAO.saveGoal("Learn SQL", "Complete SQL tutorial");
        mockGoalDAO.saveGoal("Advanced Java", "Study advanced Java topics");

        // Act & Assert for searching with keyword "Java"
        List<String[]> results = mockGoalDAO.searchGoals("Java");
        assertEquals(2, results.size());
        assertEquals("Learn Java", results.get(0)[0]);
        assertEquals("Advanced Java", results.get(1)[0]);

        // Act & Assert for searching with keyword "SQL"
        results = mockGoalDAO.searchGoals("SQL");
        assertEquals(1, results.size());
        assertEquals("Learn SQL", results.get(0)[0]);
    }

    @Test
    public void testDeleteNonExistentGoal() {
        // Arrange
        mockGoalDAO.saveGoal("Learn Java", "Complete Java tutorial");

        // Act
        mockGoalDAO.deleteGoal("Nonexistent Goal");

        // Assert
        List<String[]> goals = mockGoalDAO.getAllGoals();
        assertEquals(1, goals.size());  // The goal should still be there
    }
}
