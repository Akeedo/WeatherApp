package com.mycompany.weather;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user){
        entityManager.persist(user);
    }

    public User findUserByUsernames(String username){
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
