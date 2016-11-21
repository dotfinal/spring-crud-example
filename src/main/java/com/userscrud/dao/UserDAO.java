package com.userscrud.dao;

import com.userscrud.models.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    List<User> findByName(String name);

    User getUserById(int id);

    boolean exists(String name);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
