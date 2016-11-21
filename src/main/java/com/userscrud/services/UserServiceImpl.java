package com.userscrud.services;

import com.userscrud.dao.UserDAO;
import com.userscrud.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public List<User> findByName(String name) {
        return userDAO.findByName(name);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public boolean addUser(User user) {
        if (userDAO.exists(user.getName())) {
            return false;
        } else {
            userDAO.addUser(user);
            return true;
        }
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
