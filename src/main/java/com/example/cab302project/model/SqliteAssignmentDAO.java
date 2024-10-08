package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * SqliteAssignmentDAO provides data access methods for managing Assignment entities
 */
public class SqliteAssignmentDAO implements IAssignmentDAO{
    private Connection connection;

    /**
     * Constructor that initializes the database connection
     * and creates the assignments table.
     */
    public SqliteAssignmentDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the assignments table if it does not already exist.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS assignments (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "description TEXT, " +
                    "grade REAL DEFAULT 0, " +
                    "username TEXT NOT NULL, " +
                    "subjectId INTEGER NOT NULL, " +
                    "dueDate VARCHAR NOT NULL, " +
                    "status VARCHAR NOT NULL, " +
                    "FOREIGN KEY (subjectId) REFERENCES subjects(id) ON DELETE CASCADE" + // Foreign key constraint
                    ");";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions
        }
    }
    /**
     * Retrieves a specific assignment by its ID.
     *
     * @param id The ID of the assignment to retrieve.
     * @return The Assignment object if found, or null if not found.
     */
    @Override
    public Assignment getAssignment(int id) {
        // Variable to store the assignment
        Assignment assignment = null;

        String query = "SELECT a.*, s.code, s.name FROM assignments a " +
                "JOIN subjects s ON a.subjectId = s.id " +
                "WHERE a.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Check if a result was found
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float grade = resultSet.getFloat("grade");
                String username = resultSet.getString("username");
                int subjectId = resultSet.getInt("subjectId");
                String dueDate = resultSet.getString("dueDate");
                String status = resultSet.getString("status");

                // Get subject details
                String subjectCode = resultSet.getString("code");
                String subjectName = resultSet.getString("name");
                Subject subject = new Subject(subjectId, subjectCode, subjectName, "");

                // Create the assignment object
                assignment = new Assignment(name, description, username, subject, dueDate);
                assignment.setId(id);
                assignment.setGrade(grade);
                assignment.setStatus(AssignmentStatus.valueOf(status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return assignment;
    }
    /**
     * Searches for assignments that match the given keyword in their name.
     *
     * @param keyword The keyword to search for in assignment names.
     * @return A list of matching Assignment objects.
     */
    public List<Assignment> searchAssignments(String keyword) {
        List<Assignment> matchingAssignments = new ArrayList<>();

        String query = "SELECT a.*, s.code, s.name FROM assignments a " +
                "JOIN subjects s ON a.subjectId = s.id " +
                "WHERE LOWER(a.name) LIKE LOWER(?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Add wildcard for more broad result
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                float grade = resultSet.getFloat("grade");
                String username = resultSet.getString("username");
                int subjectId = resultSet.getInt("subjectId");
                String dueDate = resultSet.getString("dueDate");
                String status = resultSet.getString("status");
                // Get subject details
                String subjectCode = resultSet.getString("code");
                String subjectName = resultSet.getString("name");
                Subject subject = new Subject(subjectId, subjectCode, subjectName, "");

                Assignment assignment = new Assignment(name, description, username, subject, dueDate);
                assignment.setId(id);
                assignment.setGrade(grade);
                assignment.setStatus(AssignmentStatus.valueOf(status));
                matchingAssignments.add(assignment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchingAssignments;
    }

    /**
     * Adds a new assignment to the database.
     *
     * @param assignment The Assignment object to be added.
     */
    @Override
    public void addAssignment(Assignment assignment) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO assignments (name, description, grade, username, subjectId, dueDate, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, assignment.getName());
            statement.setString(2, assignment.getDescription());
            statement.setFloat(3, assignment.getGrade());
            statement.setString(4, assignment.getUsername());
            statement.setInt(5, assignment.getSubject().getId());
            statement.setString(6, assignment.getDueDate());
            statement.setString(7, assignment.getStatus().name());
            statement.executeUpdate();
            // Set the ID of the new assignment
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                assignment.setId(generatedKeys.getInt(1));
            }
        } catch (org.sqlite.SQLiteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all assignments for a specific user.
     *
     * @param username The username of the user whose assignments are to be retrieved.
     * @return A list of Assignment objects for the specified user.
     */
    @Override
    public List<Assignment> getAllAssignments(String username) {
        List<Assignment> assignments = new ArrayList<>();
        try {
            String query = "SELECT a.*, s.code, s.name FROM assignments a " +
                    "JOIN subjects s ON a.subjectId = s.id " +
                    "WHERE a.username = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    float grade = resultSet.getFloat("grade");
                    int subjectId = resultSet.getInt("subjectId");
                    String dueDate = resultSet.getString("dueDate");
                    String status = resultSet.getString("status");

                    // Get subject details
                    String subjectCode = resultSet.getString("code");
                    String subjectName = resultSet.getString("name");
                    Subject subject = new Subject(subjectId, subjectCode, subjectName, "");
                    Assignment assignment = new Assignment(name, description, username, subject, dueDate);
                    assignment.setId(id);
                    assignment.setGrade(grade);
                    assignment.setStatus(AssignmentStatus.valueOf(status));
                    assignments.add(assignment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignments;
    }

    /**
     * Updates an existing assignment in the database.
     *
     * @param assignment The Assignment object containing updated data.
     */
    @Override
    public void updateAssignment(Assignment assignment) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE assignments SET name = ?, description = ?, grade = ?, subjectId = ?, dueDate = ?, status = ? WHERE id = ?");
            statement.setString(1, assignment.getName());
            statement.setString(2, assignment.getDescription());
            statement.setFloat(3, assignment.getGrade());
            statement.setInt(4, assignment.getSubject().getId());
            statement.setString(5, assignment.getDueDate());
            statement.setString(6, assignment.getStatus().name());
            statement.setInt(7, assignment.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an assignment from the database by its object.
     *
     * @param assignment The object of the assignment to be deleted.
     */
    @Override
    public void deleteAssignment(Assignment assignment) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM assignments WHERE id = ?");
            statement.setInt(1, assignment.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
