package com.github.alexbabkin.product;

public class Product {
    private Integer id;
    private String title;
    private Double cost;

    public Product() {
        this.id = null;
        this.title = "";
        this.cost = null;
    }

    public Product(Integer id, String title, Double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, title: %s, cost: %f", id, title, cost);
    }
}
