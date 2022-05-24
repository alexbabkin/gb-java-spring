package com.github.alexbabkin.product;

public class Product {
    private Integer id;
    private String title;
    private Double coast;

    public Product() {
        id = null;
        title = "";
        coast = null;
    }

    public Product(Integer id, String title, Double coast) {
        this.id = id;
        this.title = title;
        this.coast = coast;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getCoast() {
        return coast;
    }

    @Override
    public String toString() {
        return String.format("Product id: %d title: %s coast: %f \n", this.id, this.title, this.coast);
    }
}
