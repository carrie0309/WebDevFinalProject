/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.repository.core;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author NicolasZHANG
 * @param <T>
 */
public class BaseRepository<T> {

    private Session session;
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        session = sessionFactory.openSession();
    }

    public List<T> find(String hql) {
        return getSession().createQuery(hql).list();
    }

    public List<T> findAll(Class classe) {
        return getSession().createCriteria(classe).list();
    }

    public List<T> find(String hql, Object[] args) {
        Query query = getSession().createQuery(hql);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i, args[i]);
            }
        }
        return query.list();
    }

    public T get(Class classe, int id) {
        return (T) getSession().get(classe, id);
    }

    public T update(T object) {
        getSession().saveOrUpdate(object);
        return object;
    }

    public T save(T object) {
        getSession().save(object);
        return object;
    }

    public Session getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void delete(T object) {
        final Session s = getSession();
        s.getTransaction().begin();
        s.delete(object);
        s.getTransaction().commit();
//        getSession().delete(object);
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

}
