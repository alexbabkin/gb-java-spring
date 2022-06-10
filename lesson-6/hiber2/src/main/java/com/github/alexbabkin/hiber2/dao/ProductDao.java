package com.github.alexbabkin.hiber2.dao;

import com.github.alexbabkin.hiber2.db.SessionFactoryWrapper;
import com.github.alexbabkin.hiber2.entities.Customer;
import com.github.alexbabkin.hiber2.entities.Order;
import com.github.alexbabkin.hiber2.entities.Product;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            var products = session.createQuery("select p from Product p").list();
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

    public List<Customer> getProductCustomers(Long productId) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, productId);
            List<Customer> customers =
                    product.getOrders()
                            .stream()
                            .map(Order::getCustomer)
                            .distinct()
                            .collect(Collectors.toList());
            session.getTransaction().commit();
            return customers;
        }
    }
}
