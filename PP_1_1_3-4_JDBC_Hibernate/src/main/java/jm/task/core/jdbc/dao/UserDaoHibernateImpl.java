package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = new Util().getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        //Session session = sessionFactory.openSession();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users" +
                    " (id mediumint not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age tinyint, " +
                    "PRIMARY KEY (id))").executeUpdate();
            tx.commit();
            System.out.println("Таблица готова");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            tx.commit();
            System.out.println("Удаление таблицы");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            tx.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(session.get(User.class, id));
            tx.commit();
            System.out.println("User удален");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        try {
            tx.commit();
            return userList;
        } catch (HibernateException e) {
            tx.rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE Users;").executeUpdate();
            tx.commit();
            System.out.println("Таблица очищена");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
