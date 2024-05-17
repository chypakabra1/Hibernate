package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    @Override
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name,lastName,age);
        //System.out.println("User с именем " + name + "добавлен");
    }
    @Override
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
        /*for (User user : users) {
            System.out.println(user);
        }
        return users;*/
    }
    @Override
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
