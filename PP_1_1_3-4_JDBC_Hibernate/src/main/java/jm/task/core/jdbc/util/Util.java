package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private static SessionFactory session = null;
    /*public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            if (!conn.isClosed()) {
                System.out.println("Коннект");
            }
        } catch (SQLException e) {
            System.out.println("Не коннект");
        }
        return conn;
    }*/
    public static SessionFactory getConnection() {
        if (session == null) {
            try {
                Configuration conf = new Configuration().setProperty("hibernate.connection.url", DB_URL)
                        .setProperty("hibernate.connection.username", DB_USERNAME)
                        .setProperty("hibernate.connection.password", DB_PASSWORD)
                        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                        .addAnnotatedClass(User.class);
                ServiceRegistry Registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
                session = conf.buildSessionFactory(Registry);
            } catch (Exception e) {
                System.out.println("Проблема с созданием");
                e.printStackTrace();
            }
        }
        return session;
    }
}
