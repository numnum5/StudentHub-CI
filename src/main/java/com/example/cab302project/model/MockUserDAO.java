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

    @Override
    public int searchUser(String username, String password) {

        for(User user : users){
            if(checkPassword(user.getPassword(), password) && checkUsername(user.getUsername(), username)){
                return user.getId();
            }
        }
        return -1;
    }

    // A method for checking current user's password with the given password
    @Override
    public boolean checkPassword(String userPassword, String password) {
        return userPassword != null && userPassword.equals(password);
    }

    // A method for checking current user's username with the given username
    @Override
    public boolean checkUsername(String userUsername, String username) {
        return userUsername != null && userUsername.equals(username);
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

    // Method for getting all users in the database
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
