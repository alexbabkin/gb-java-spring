package com.github.alexbabkin.hiber2.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_products")
public class OrderProduct extends AbstractProduct {

    @ManyToOne
    @JoinColumn(name = "actual_product_id")
    private Product actualProduct;

    public OrderProduct() {}

    public OrderProduct(Product product) {
        setTitle(product.getTitle());
        setCost(product.getCost());
        actualProduct = product;
    }

    public Product getActualProduct() {
        return actualProduct;
    }

    public void setActualProduct(Product actualProduct) {
        this.actualProduct = actualProduct;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(". Actual product = %s", actualProduct);
    }
}
