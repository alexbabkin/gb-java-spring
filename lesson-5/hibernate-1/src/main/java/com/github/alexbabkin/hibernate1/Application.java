package com.github.alexbabkin.hibernate1;

import com.github.alexbabkin.hibernate1.dao.ProductDao;
import com.github.alexbabkin.hibernate1.db.DbInitializer;
import com.github.alexbabkin.hibernate1.product.Product;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        DbInitializer.forcePrepareData();
        ProductDao dao = new ProductDao();

        var p = dao.findById(1L);
        p.setCost(100500);
        dao.saveOrUpdate(p);
        dao.deleteById(2L);

        List<Product> products = dao.findAll();
        products = products;
    }
}
