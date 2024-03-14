package com.example.services;

import com.example.HibernateUtil;
import com.example.model.Category;
import com.example.model.Post;
import org.hibernate.Session;

import java.util.List;

public class CategoryService {

    public void createCategory(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = new Category(name);

        session.persist(category);
        session.getTransaction().commit();
        session.close();
    }

    public void eraseCategory(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.find(Category.class, id);
        session.delete(category);

        session.getTransaction().commit();
        session.close();
    }

    public Category getCategoryById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.find(Category.class, id);

        session.getTransaction().commit();
        session.close();

        return category;
    }

    public List<Category> getCategoryList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Category> categories = session.createQuery("SELECT c FROM Category c", Category.class).getResultList();

        session.getTransaction().commit();
        session.close();

        return categories;
    }

    public void clearCategories(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM Category").executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

}
