package com.github.alexbabkin.hiber2.services;

import com.github.alexbabkin.hiber2.dao.CustomerDao;
import com.github.alexbabkin.hiber2.entities.Customer;
import com.github.alexbabkin.hiber2.entities.OrderProduct;
import com.github.alexbabkin.hiber2.entities.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Product> getCustomerProducts(Long customerId) {
        return customerDao.getCustomerProducts(customerId);
    }

    public List<OrderProduct> getCustomerOrderProducts(Long customerId) {
        return customerDao.getCustomerOrderProducts(customerId);
    }

    public Customer getCustomer(Long customerId) {
        return customerDao.findById(customerId);
    }

    public void saveOrUpdate(Customer customer) {
        customerDao.saveOrUpdate(customer);
    }
}
