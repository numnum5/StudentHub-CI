package com.example.cab302project.model;

import java.util.List;
/**
 * Interface for the User Data Access Object that handles
 * the CRUD operations for the User class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new user to the database.
     * @param user The user to add.
     */
    void addUser(User user);
    /**
     * Updates an existing user in the database.
     * @param user The user to update.
     */
    void updateUser(User user);
    /**
     * Deletes a user from the database.
     * @param user The user to delete.
     */
    void deleteUser(User user);

    /**
     * Checks if a user exists in the database.
     * @param userName The username of the user to delete.
     */
    boolean userExists(String userName);

    /**
     * checks if user's password is correct.
     * @param userName The username of the user to delete.
     * @param password The password of the user to delete.
     */
    boolean passwordCorrect(String userName, String password);

    /**
     * Retrieves a user from the database.
     * @param id The id of the user to retrieve.
     * @return The user with the given id, or null if not found.
     */

    User getUser(int id);
    /**
     * Retrieves all users from the database.
     * @return A list of all users in the database.
     */
    List<User> getAllUsers();
}
