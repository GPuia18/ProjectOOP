package com.example.demo.service;

import com.example.demo.entities.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(int Id);

    public User addOrUpdateUser(User user);

    public User login(String username, String password);

    public void addUser(String firstName, String lastName, String username, String password);

    public User deleteUser(int Id) throws Exception;
}
