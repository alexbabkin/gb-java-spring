package com.github.alexbabkin.hibernate1.dao;

import com.github.alexbabkin.hibernate1.product.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Using transactions while querying the database has significance once multiple people/processes
// are using the database at the same time. (i.e. concurrency)
// With using transaction boundaries you are eliminating (at least some of) the problems that are
// introduced by concurrent database usage.

// so we will use transaction even for read ops

public class ProductDao {
    private final SessionFactory factory =
            new Configuration().configure("configs/hibernate.cfg.xml").buildSessionFactory();

    public Product findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            var product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            var products = session.createQuery("from Product").list();
            session.getTransaction().commit();
            return products;
        }
    }

    public void deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :productId")
                    .setParameter("productId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }
}
