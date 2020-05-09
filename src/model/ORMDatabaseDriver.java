package model;

import classesORM.FreelancerORM;
import classesORM.UserORM;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.Id;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ORMDatabaseDriver {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession () throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static List selectObjects (String sql_code){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        List results = new ArrayList();
        try {
            tx = session.beginTransaction();
            results = session.createQuery(sql_code).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return results;
        }
    }

    public static int insertObject(Object object){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        int ID = 0;

        try {
            tx = session.beginTransaction();
            ID = (Integer) session.save(object);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ID;
    }
}
