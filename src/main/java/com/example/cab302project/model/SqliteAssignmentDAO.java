package com.example.cab302project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteAssignmentDAO {
    private Connection connection;
    public boolean userUnique;

    public SqliteAssignmentDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query =  "CREATE TABLE IF NOT EXISTS Assignment (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +          // Unique identifier
                    "name TEXT NOT NULL, " +                            // Name of the assignment
                    "description TEXT, " +                              // Description of the assignment
                    "grade REAL DEFAULT 0, " +                          // Grade (float in Java corresponds to REAL in SQLite)
                    "username TEXT NOT NULL, " +                        // Username associated with the assignment
                    "subjectId INTEGER NOT NULL, " +                    // Foreign key to the Subject table
                    "dueDate VARCHAR NOT NULL, " +                         // Due date as a string
                    "status VARCHAR NOT NULL" +                            // Assignment status
                    ");";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAssignment(Assignment assignment) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Assignment (name, description, grade, username, subjectId, dueDate, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, assignment.getName());
            statement.setString(2, assignment.getDescription());
            statement.setFloat(3, assignment.getGrade());
            statement.setString(4, assignment.getUsername());
            statement.setInt(5, assignment.getSubject().getId());
            statement.setString(6, assignment.getDueDate());
            statement.setString(7, assignment.getStatus().name());
            statement.executeUpdate();
            // Set the id of the new contact
            ResultSet generatedKeys = statement.getGeneratedKeys();
            userUnique = true;
            if (generatedKeys.next()) {
                assignment.setId(generatedKeys.getInt(1));
            }
        }

        catch (org.sqlite.SQLiteException e)
        {
            userUnique = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET firstName = ?, lastName = ?, school = ?, email = ?, phone = ?, address = ? WHERE id = ?");
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
//            statement.setString(6, user.getSchool());
//            statement.setString(7, user.getEmail());
//            statement.setString(8, user.getPhone());
//            statement.setString(9, user.getAddress());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int searchUser(String username, String password) {
        return 0;
    }


    public boolean checkPassword(String userPassword, String password) {
        return false;
    }


    public boolean checkUsername(String userUsername, String username) {
        return false;
    }


    public User getUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
//                String phone = resultSet.getString("phone");
//                String email = resultSet.getString("email");
//                String school = resultSet.getString("school");
//                String address = resultSet.getString("address");
                User user = new User(firstName, lastName, username, password);
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                User user = new User(firstName, lastName, phone, email);
                user.setId(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean userExists(String userName) {
        try {
            String query = "SELECT * FROM users WHERE username = '" + userName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean passwordCorrect(String userName, String password)
    {
        try {
            String query = "SELECT * FROM users WHERE username = '" + userName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            String storedPassword = resultSet.getString("password");
            return storedPassword.equals(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
