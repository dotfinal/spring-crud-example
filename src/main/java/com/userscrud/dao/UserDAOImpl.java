package com.userscrud.dao;

import com.userscrud.models.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public List<User> findByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User as user where user.name = :name");
        query.setString("name", name);
        return query.list();
    }

    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public boolean exists(String name) {
        if (name != null) {
            Object object = sessionFactory.getCurrentSession().createQuery("from User where name = ?").setString(0, name).setMaxResults(1).uniqueResult();
            return object != null;
        }
        return false;
    }

    public void addUser(User user) {
        if (user != null) {
            sessionFactory.getCurrentSession().save(user);
        }
    }

    public void updateUser(User newUser) {
        if (newUser != null) {
            User user = getUserById(newUser.getId());
            if (user != null) {
                user.copyFrom(newUser);
                sessionFactory.getCurrentSession().update(user);
            }
        }
    }

    public void deleteUser(User user) {
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
