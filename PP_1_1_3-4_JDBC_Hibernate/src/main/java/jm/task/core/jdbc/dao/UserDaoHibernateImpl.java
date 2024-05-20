package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
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
            //System.out.println("Таблица готова");
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
            //System.out.println("Удаление таблицы");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
            //System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.delete(session.get(User.class, id));
            tx.commit();
            //System.out.println("User удален");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            list = session.createCriteria(User.class).list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()){
            tx = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE Users;").executeUpdate();
            tx.commit();
            //System.out.println("Таблица очищена");
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
