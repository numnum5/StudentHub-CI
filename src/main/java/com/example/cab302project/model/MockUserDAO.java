package com.example.cab302project.model;

import java.util.ArrayList;
import java.util.List;

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
        for (User contact : users) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    // Method for getting all users in the database
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
