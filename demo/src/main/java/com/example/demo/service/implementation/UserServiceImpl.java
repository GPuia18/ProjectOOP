package com.example.demo.service.implementation;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int Id) {
        return userRepository.findById(Id).orElse(null);
    }

    @Override
    public User addOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    @Override
    public void addUser(String firstName, String lastName, String username, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public User deleteUser(int Id) throws Exception {
        User delUser = null;
        try {
            delUser = userRepository.findById(Id).orElse(null);
            if (delUser == null) {
                throw new Exception("can't find user");
            } else {
                userRepository.deleteById(Id);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return delUser;
    }
}
