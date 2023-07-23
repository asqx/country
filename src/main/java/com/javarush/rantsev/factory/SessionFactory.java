package com.javarush.rantsev.factory;

import com.javarush.rantsev.entity.City;
import com.javarush.rantsev.entity.Country;
import com.javarush.rantsev.entity.CountryLanguage;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class SessionFactory {
    private org.hibernate.SessionFactory sessionFactory;

    public SessionFactory() {
        sessionFactory = prepareRelationalDb();
    }
    public org.hibernate.SessionFactory prepareRelationalDb() {
        final org.hibernate.SessionFactory sessionFactory;
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/world");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.STATEMENT_BATCH_SIZE, "100");

        sessionFactory = new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .addProperties(properties)
                .buildSessionFactory();
        return sessionFactory;
    }

    public org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
