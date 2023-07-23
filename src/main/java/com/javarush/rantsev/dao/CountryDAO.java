package com.javarush.rantsev.dao;

import com.javarush.rantsev.entity.Country;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
@Getter
public class CountryDAO {
    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getAll() {
        Query<Country> query = sessionFactory.getCurrentSession().createQuery("select c from Country c", Country.class);
        return query.list();
    }
}
