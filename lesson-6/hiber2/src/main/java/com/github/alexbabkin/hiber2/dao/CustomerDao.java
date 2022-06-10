package com.github.alexbabkin.hiber2.dao;

import com.github.alexbabkin.hiber2.db.SessionFactoryWrapper;
import com.github.alexbabkin.hiber2.entities.Customer;
import com.github.alexbabkin.hiber2.entities.Order;
import com.github.alexbabkin.hiber2.entities.OrderProduct;
import com.github.alexbabkin.hiber2.entities.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDao {
    private final SessionFactoryWrapper sessionFactoryWrapper;

    @Autowired
    public CustomerDao(SessionFactoryWrapper sessionFactoryWrapper) {
        this.sessionFactoryWrapper = sessionFactoryWrapper;
    }

    public void saveOrUpdate(Customer customer) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
        }
    }

    public Customer findById(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }

    public List<Order> getCustomerOrders(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            List<Order> customerOrders = new ArrayList<>(customer.getOrders());
            session.getTransaction().commit();
            return customerOrders;
        }
    }

    public List<Product> getCustomerProducts(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            List<Product> products =
                    customer.getOrders()
                            .stream()
                            .map(Order::getProducts)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
            session.getTransaction().commit();
            return products;
        }
    }

    public List<OrderProduct> getCustomerOrderProducts(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            List<OrderProduct> products =
                    customer.getOrders()
                            .stream()
                            .map(Order::getOrderProducts)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
            session.getTransaction().commit();
            return products;
        }
    }

    public void deleteById(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Customer c where c.id = :customerId")
                    .setParameter("customerId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
