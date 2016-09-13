/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.webtoolsfinal.dao;

import edu.neu.webtoolsfinal.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public abstract class BaseDAO<T> {

    public List<T> findAll(Class type) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session.createCriteria(type).list();
    }

    public T get(Class type, int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return (T) session.get(type, id);
    }

    public void add(T newEntity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(newEntity);
    }

    public void delete(Class type, int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Object entity = session.get(type, id);
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

}
