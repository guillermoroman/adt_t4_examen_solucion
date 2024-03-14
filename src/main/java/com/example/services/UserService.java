package com.example.services;

import com.example.HibernateUtil;
import com.example.model.Post;
import com.example.model.User;
import org.hibernate.Session;

import java.util.List;

public class UserService {
    public void createUser(String name, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user1 = new User(name, email);
        session.persist(user1);

        session.getTransaction().commit();
        session.close();
    }

    public void eraseUser(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.find(User.class, id);
        if (user != null) { // Asegúrate de que el usuario exista antes de intentar borrarlo
            session.remove(user);
        } else {
            // Manejo opcional: puedes lanzar una excepción personalizada o registrar que el usuario no fue encontrado.
            System.out.println("Usuario con ID " + id + " no encontrado y no puede ser borrado.");
        }

        session.getTransaction().commit();
        session.close();
    }

    public User getUserById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.find(User.class, id);

        session.getTransaction().commit();
        session.close();

        return user;
    }

    public User getUserByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = (User) session.createQuery("from User where name = :name")
                .setParameter("name", name)
                .uniqueResult();

        session.getTransaction().commit();
        session.close();

        return user;
    }

    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User", User.class).getResultList();

        session.getTransaction().commit();
        session.close();

        return users;
    }

    public void clearUsers(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM User").executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

}
