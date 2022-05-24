package com.github.alexbabkin.app;

import com.github.alexbabkin.cart.Cart;
import com.github.alexbabkin.config.AppConfiguration;
import com.github.alexbabkin.product.Repository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var repository = context.getBean(Repository.class);
        var cart = context.getBean(Cart.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Type a command");
                String command = reader.readLine();
                switch (command) {
                    case "all":
                        System.out.println(repository.getAll());
                        break;
                    case "new":
                        cart = context.getBean(Cart.class);
                        break;
                    case "add":
                        System.out.println("choose id");
                        int idToAdd = Integer.parseInt(reader.readLine());
                        cart.add(idToAdd);
                        break;
                    case "remove":
                        System.out.println("choose id");
                        int idToRemove = Integer.parseInt(reader.readLine());
                        cart.remove(idToRemove);
                        break;
                    case "clear":
                        cart.clear();
                        break;
                    case "show":
                        cart.show();
                        break;
                    case "q":
                        context.close();
                        return;
                    default:
                        System.out.println("unrecognized command");
                }
            } catch (IOException e) {
                System.out.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }
}
