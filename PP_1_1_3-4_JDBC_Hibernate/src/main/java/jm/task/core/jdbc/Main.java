package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Успешно загружено");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        System.out.println("User c имненем " + userService.getAllUsers().toString() + " добавлен в базу данных");
        //userService.getAllUsers();
        //userService.cleanUsersTable();
        //userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
