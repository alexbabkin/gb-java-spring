package com.github.alexbabkin.hiber2.db;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class SessionFactoryWrapper {
    public final SessionFactory factory =
            new Configuration().configure("configs/hibernate.cfg.xml").buildSessionFactory();

    @PostConstruct
    public void populate() {
        Session session = null;
        try {
            URI scriptUri =
                    Objects.requireNonNull(
                                    SessionFactoryWrapper.class
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
            if (session != null) {
                session.close();
            }
        }
    }

    public SessionFactory getFactory() {
        return factory;
    }
}
