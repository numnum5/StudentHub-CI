package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for simulating access to database for user table
 */
public class MockUserDAO implements IUserDAO {
    public static final ArrayList<User> users = new ArrayList<>();
    private static int autoIncrementedId = 0;

    // Method for adding a user
    @Override
    public void addUser(User user) {
        user.setId(autoIncrementedId);
        autoIncrementedId++;
        users.add(user);
    }

    // Method for updating user
    @Override
    public void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()) {
                users.set(i, user);
                break;
            }
        }
    }

    // Method for deleting a user
    @Override
    public void deleteUser(User user) {
        users.remove(user);
    }


    // Method for getting all a user in the database given id
    @Override
    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    //Method to check if user exists.
    @Override
    public boolean userExists(String userName)
    {
        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return true; // Return true if a match is found
            }
        }
        return false; // Return false only after checking all users
    }

    // Method to check if user password is the same for a certain user.
    @Override
    public boolean passwordCorrect(String userName, String password)
    {
        for (User user : users)
        {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password))
            {
                return true; // Return true if a match is found
            }
        }
        return false; // Return false if a match is found
    }

    // Method for getting all users in the database
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
