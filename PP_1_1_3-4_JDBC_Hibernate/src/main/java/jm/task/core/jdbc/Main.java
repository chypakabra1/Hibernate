package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        //userDao.createUsersTable();
        //userDao.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Tantyana", "Fedorova", (byte) 25);
        userService.saveUser("Tantyana", "Fedorova", (byte) 25);
        userService.saveUser("Tantyana", "Fedorova", (byte) 25);
        userService.saveUser("Olga", "Petrova",(byte) 34);
        userService.saveUser("Igor", "Serov", (byte) 26);
        userService.saveUser("Nikolay", "Egorov", (byte) 23);

        //userDao.removeUserById(1);
        //userDao.getAllUsers();
        //userDao.cleanUsersTable();
        //userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
    /*public final class Util {
        private Util() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Драйвер загружен");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
}
