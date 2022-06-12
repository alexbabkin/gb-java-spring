package com.github.alexbabkin.hibernate1.db;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbInitializer {
    public static void forcePrepareData() {
        SessionFactory factory =
                new Configuration().configure("configs/hibernate.cfg.xml").buildSessionFactory();
        Session session = null;
        try {
            URI scriptUri =
                    Objects.requireNonNull(
                                    DbInitializer.class
                                            .getClassLoader()
                                            .getResource("sql/populate.sql"))
                            .toURI();
            String sql = Files.lines(Paths.get(scriptUri)).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
