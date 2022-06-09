package com.github.alexbabkin.hiber2.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.alexbabkin.hiber2.db.SessionFactoryWrapper;
import com.github.alexbabkin.hiber2.entities.Product;

@Component
public class ProductDao {
    private final SessionFactoryWrapper sessionFactoryWrapper;

    @Autowired
    public ProductDao(SessionFactoryWrapper sessionFactoryWrapper) {
        this.sessionFactoryWrapper = sessionFactoryWrapper;
    }

    public Product findById(Long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            var product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            var products = session.createQuery("from Product").list();
            session.getTransaction().commit();
            return products;
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :productId")
                    .setParameter("productId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }
}
