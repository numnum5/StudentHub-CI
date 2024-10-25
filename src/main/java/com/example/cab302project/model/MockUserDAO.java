package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for simulating access to the database for user-related operations.
 * This class implements the IUserDAO interface and provides methods to
 * add, update, delete, and retrieve user information.
 */
public class MockUserDAO implements IUserDAO {
    public static final ArrayList<User> users = new ArrayList<>(); // List to store user objects
    private static int autoIncrementedId = 0; // Auto-incremented ID for users

    /**
     * Method for adding a user to the mock database.
     * Assigns an auto-incremented ID to the user and adds it to the list.
     *
     * @param user The user object to be added.
     */
    @Override
    public void addUser(User user) {
        user.setId(autoIncrementedId);
        autoIncrementedId++;
        users.add(user);
    }

    /**
     * Method for updating a user's information in the mock database.
     *
     * @param user The user object with updated information.
     */
    @Override
    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                break; // Exit the loop after updating
            }
        }
    }

    /**
     * Method for deleting a user from the mock database.
     *
     * @param user The user object to be deleted.
     */
    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }

    /**
     * Method for retrieving a user from the mock database given an ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return The user object if found, otherwise null.
     */
    @Override
    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user; // Return the user if a match is found
            }
        }
        return null; // Return null if no match is found
    }

    /**
     * Method to check if a user exists in the mock database based on username.
     *
     * @param userName The username to be checked.
     * @return true if the user exists, otherwise false.
     */
    @Override
    public boolean userExists(String userName) {
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return true; // Return true if a match is found
            }
        }
        return false; // Return false if no match is found
    }

    /**
     * Method to check if the password is correct for a given user.
     *
     * @param userName The username of the user to check.
     * @param password The password to verify.
     * @return true if the password is correct, otherwise false.
     */
    @Override
    public boolean passwordCorrect(String userName, String password) {
        for (User user : users) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
                return true; // Return true if a match is found
            }
        }
        return false; // Return false if no match is found
    }

    /**
     * Method for retrieving all users in the mock database.
     *
     * @return A list of all user objects.
     */
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users); // Return a new list containing all users
    }
}

