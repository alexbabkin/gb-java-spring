package com.github.alexbabkin.springboot.models;

public class Product {
    private Long id;
    private String title;
    private Double cost;

    public Product() {
        this.id = null;
        this.title = "";
        this.cost = null;
    }

    public Product(Long id, String title, Double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
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
