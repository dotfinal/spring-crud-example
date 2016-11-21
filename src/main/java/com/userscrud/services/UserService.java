package com.userscrud.services;

import com.userscrud.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<User> findByName(String name);

    User getUserById(int id);

    boolean addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
