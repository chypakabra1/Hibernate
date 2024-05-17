package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded success");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //userDao.createUsersTable();
        //userDao.dropUsersTable();
        userDao.createUsersTable();
        userDao.saveUser("Tantyana", "Fedorova", (byte) 25);
        userDao.saveUser("Tantyana", "Fedorova", (byte) 25);
        userDao.saveUser("Tantyana", "Fedorova", (byte) 25);
        userDao.saveUser("Olga", "Petrova",(byte) 34);
        userDao.saveUser("Igor", "Serov", (byte) 26);
        userDao.saveUser("Nikolay", "Egorov", (byte) 23);

        //userDao.removeUserById(1);
        //userDao.getAllUsers();
        //userDao.cleanUsersTable();
        //userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
