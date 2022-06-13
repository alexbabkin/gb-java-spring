package com.github.alexbabkin.hiber2;

import com.github.alexbabkin.hiber2.configs.AppConfiguration;
import com.github.alexbabkin.hiber2.entities.Customer;
import com.github.alexbabkin.hiber2.entities.Order;
import com.github.alexbabkin.hiber2.entities.OrderProduct;
import com.github.alexbabkin.hiber2.entities.Product;
import com.github.alexbabkin.hiber2.services.CustomerService;
import com.github.alexbabkin.hiber2.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// @SpringBootApplication
public class Hiber2Application {

    public static void main(String[] args) {
        // SpringApplication.run(Hiber2Application.class, args);
        var context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var productService = context.getBean(ProductService.class);
        var customerService = context.getBean(CustomerService.class);

        Customer customer = customerService.getCustomer(1L);
        List<Product> products = productService.getAll();
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderProducts(
                products.stream().map(OrderProduct::new).collect(Collectors.toList()));
        order.setProducts(products);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        customer.setOrders(orders);
        customerService.saveOrUpdate(customer);

        List<Product> customerProducts = customerService.getCustomerProducts(1L);
        System.out.println(customerProducts);
        List<OrderProduct> customerOrderProducts = customerService.getCustomerOrderProducts(1L);
        System.out.println(customerOrderProducts);
        List<Customer> productCustomers = productService.getProductCustomers(1L);
        System.out.println(productCustomers);
    }
}
