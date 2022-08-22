package com.board.demo.repository;

import com.board.demo.domain.Board;
import com.board.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Repository

public interface UserRepository  extends JpaRepository<User, Long> {
//    @PersistenceContext
//    private static EntityManager em;
//
//    public static Long save(User user) {
//        em.persist(user);
//        return user.getId();
//    }
//
//    public User findOne(Long id) {
//        return em.find(User.class, id);
//    }
//
//    public List<User> findAll() {
//        return em.createQuery("select m from User m", User.class)
//                .getResultList();
//    }
//    public List<User> findByName(String name) {
//        return em.createQuery("select m from User m where m.username = :name",
//                        User.class)
//                .setParameter("name", name)
//                .getResultList();
//    }
}