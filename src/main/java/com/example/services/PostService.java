package com.example.services;

import com.example.HibernateUtil;
import com.example.model.Category;
import com.example.model.Post;
import com.example.model.User;
import org.hibernate.Session;

import java.util.List;

public class PostService {
    public void createPost(String content, Long userId) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Post post = new Post(content);

        UserService userService = new UserService();
        User user = userService.getUserById(userId);

        post.setAuthor(user);

        session.persist(post);
        session.getTransaction().commit();

        session.close();

    }



    public List<Post> getAllPosts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Post> posts = session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.categories", Post.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return posts;

    }

    public List<Post> getPostsByUser(Long userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Post> posts = session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.categories WHERE p.author.id = :userId", Post.class)
                .setParameter("userId", userId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return posts;
    }

    public Post getPostById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Post post = session.find(Post.class, id);

        session.getTransaction().commit();
        session.close();

        return post;
    }

    public List<Post> getPostsByCategory(Long categoryId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Post> posts = session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.categories c WHERE c.id = :categoryId", Post.class)
                .setParameter("categoryId", categoryId)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return posts;
    }

    public void addCategoryToPost(Long postId, Long categoryId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Post post = session.find(Post.class, postId);
        Category category = session.find(Category.class, categoryId);

        post.getCategories().add(category);

        session.update(post);
        session.getTransaction().commit();
        session.close();
    }

    
}
