package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * SqliteSubjectDAO provides data access methods for managing Subject entities
 */
public class SqliteSubjectDAO implements ISubjectDAO {
    private Connection connection;
    /**
     * Constructor that initializes the database connection,
     * creates the subjects table, and pre-fills it with data if empty.
     */
    public SqliteSubjectDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
        prefillData();
    }
    /**
     * Creates the subjects table if it does not already exist.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS subjects (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "code VARCHAR NOT NULL, " +
                    "name VARCHAR NOT NULL, " +
                    "description TEXT, " +
                    "semester INTEGER NOT NULL" +
                    ");";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pre-fills the subjects table with initial data if the table is empty.
     */

    private void prefillData() {
        if (getAllSubjects().isEmpty()) { // Check if the table is empty
            try {
                addSubject(new Subject(1, "CAB502", "Advanced Quantum Computing", "Idk"));
                addSubject(new Subject(1, "CAB503", "Advanced Discrete Mathematics", "Idk"));
                addSubject(new Subject(2, "CAB504", "Advanced Data Structures", "Idk"));
                addSubject(new Subject(2, "CAB505", "Machine Learning", "Idk"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a new subject to the database.
     *
     * @param subject The Subject object to be added.
     */
    @Override
    public void addSubject(Subject subject) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO subjects (code, name, description, semester) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, subject.getUnitCode());
            statement.setString(2, subject.getName());
            statement.setString(3, subject.getDescription());
            statement.setInt(4, subject.getSemester());
            statement.executeUpdate();
            // Set the ID of the new subject
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                subject.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing subject in the database.
     *
     * @param subject The Subject object containing updated data.
     */
    @Override
    public void updateSubject(Subject subject) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE subjects SET code = ?, name = ?, description = ?, semester = ? WHERE id = ?");
            statement.setString(1, subject.getUnitCode());
            statement.setString(2, subject.getName());
            statement.setString(3, subject.getDescription());
            statement.setInt(4, subject.getSemester());
            statement.setInt(5, subject.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a subject from the database by its ID.
     *
     * @param subject The object of the subject to be deleted.
     */
    @Override
    public void deleteSubject(Subject subject) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM subjects WHERE id = ?");
            statement.setInt(1, subject.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a subject from the database by its ID.
     *
     * @param id The ID of the subject to retrieve.
     * @return The Subject object if found, or null if not found.
     */
    @Override
    public Subject getSubject(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM subjects WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int semester = resultSet.getInt("semester");
                return new Subject(semester, code, name, description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all subjects from the database.
     *
     * @return A list of all Subject objects.
     */
    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM subjects");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int semester = resultSet.getInt("semester");
                Subject subject = new Subject(semester, code, name, description);
                subject.setId(id);
                subjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }
    /**
     * Searches for subjects that match the given keyword in their name or code.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching Subject objects.
     */
    @Override
    public List<Subject> searchSubjects(String keyword) {
        List<Subject> matchingSubjects = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return matchingSubjects;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM subjects WHERE name LIKE ? OR code LIKE ?");
            // Use wildcards for search
            String searchPattern = "%" + keyword + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int semester = resultSet.getInt("semester");
                Subject subject = new Subject(semester, code, name, description);
                subject.setId(id);
                matchingSubjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchingSubjects;
    }
}