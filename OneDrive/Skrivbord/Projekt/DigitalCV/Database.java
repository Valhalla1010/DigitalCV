package com.example.digitalcv;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Named
public class Database {
    @PersistenceContext
    EntityManager em;
    @Transactional
    public void saveUser(USER user){
        em.persist(user);
    }

    @Transactional
    public boolean check(String username, String password){
        List<USER> users = em.createQuery("SELECT u FROM USER u WHERE u.Username = :username AND u.Password = :password"
        , USER.class).setParameter("username", username)
                .setParameter("password", password).getResultList();
        return !users.isEmpty();
    }

    public List<USER> getuser(){
        List<USER> getuser = em.createQuery("SELECT u FROM USER u", USER.class ).getResultList();
        return getuser;
    }
}
