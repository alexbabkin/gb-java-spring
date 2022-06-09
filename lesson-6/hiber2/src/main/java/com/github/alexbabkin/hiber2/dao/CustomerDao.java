package com.github.alexbabkin.hiber2.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.alexbabkin.hiber2.db.SessionFactoryWrapper;
import com.github.alexbabkin.hiber2.entities.Customer;
import com.github.alexbabkin.hiber2.entities.Product;

@Component
public class CustomerDao {
    private final SessionFactoryWrapper sessionFactoryWrapper;

    @Autowired
    public CustomerDao(SessionFactoryWrapper sessionFactoryWrapper) {
        this.sessionFactoryWrapper = sessionFactoryWrapper;
    }

    public void createCustomer(String name) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer c = new Customer(name);
            session.save(c);
            session.getTransaction().commit();
        }
    }

    public Customer getCustomer(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            List<Order> ol = c.getOrders();
            session.getTransaction().commit();
            return c;
        }
    }

    public List<Order> getCustomerOrdersById(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            List<Order> ol = new ArrayList<>();
            ol.addAll(c.getOrders());
            session.getTransaction().commit();
            return ol;
        }
    }

    public List<Product> getCustomerProductListById(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            List<Order> ol = c.getOrders();
            List<Product> pl = new ArrayList<>();
            for (Order o : ol) {
                for (OrderDetails od : o.getOrderDetails()) {
                    if (pl.contains(od.getProduct())) continue;
                    pl.add(od.getProduct());
                }
            }
            session.getTransaction().commit();
            return pl;
        }
    }

    public void updateCustomer(long id, String name) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            c.setName(name);
            session.getTransaction().commit();
        }
    }

    public  void deleteCustomer(long id) {
        try (Session session = sessionFactoryWrapper.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer c = session.get(Customer.class, id);
            session.delete(c);
            session.getTransaction().commit();
        }
    }
}
