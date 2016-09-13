/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.service;

import edu.neu.webtoolsfinal.entity.Role;
import edu.neu.webtoolsfinal.entity.User;
import edu.neu.webtoolsfinal.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User get(int id) {
        return userRepository.get(User.class, id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> quickSearch(String gender, String seekingGender, Integer minAge, Integer maxAge, String country, String state, String city) {
        return userRepository.quickSearch(gender, seekingGender, minAge, maxAge, country, state, city);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void addUser(String username, String realName, String email, Role role, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(realName);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);

    }

    public List<User> findAll() {
        return userRepository.findAll(User.class);
    }

    public User findByUserId(int userId) {
        return userRepository.findByUserId(userId);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
