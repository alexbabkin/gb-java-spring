package com.github.alexbabkin.hiber2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_products")
public class OrderProduct {

    @ManyToOne
    @JoinColumn(name = "actual_product_id")
    private Product actualProduct;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Long cost;


    public OrderProduct() {}

    public OrderProduct(Product product) {
        setTitle(product.getTitle());
        setCost(product.getCost());
        actualProduct = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Product getActualProduct() {
        return actualProduct;
    }

    public void setActualProduct(Product actualProduct) {
        this.actualProduct = actualProduct;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "actualProduct=" + actualProduct +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
