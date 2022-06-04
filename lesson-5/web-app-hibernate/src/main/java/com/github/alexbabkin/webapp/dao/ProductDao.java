package com.github.alexbabkin.webapp.dao;

// Using transactions while querying the database has significance once multiple people/processes
// are using the database at the same time. (i.e. concurrency)
// With using transaction boundaries you are eliminating (at least some of) the problems that are
// introduced by concurrent database usage.

// so we will use transaction even for read ops

import com.github.alexbabkin.webapp.db.SessionWrapper;
import com.github.alexbabkin.webapp.models.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {
    private final SessionFactory factory =
            new Configuration().configure("configs/hibernate.cfg.xml").buildSessionFactory();

    private final SessionWrapper sessionWrapper;

    @Autowired
    public ProductDao(SessionWrapper sessionWrapper) {
        this.sessionWrapper = sessionWrapper;
    }

    public Product findById(Long id) {
        try (Session session = sessionWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            var product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        try (Session session = sessionWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            var products = session.createQuery("from Product").list();
            session.getTransaction().commit();
            return products;
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :productId")
                    .setParameter("productId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }
}
