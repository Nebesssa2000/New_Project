package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_db";
    private static final String DB_USERNAME = "Victor";
    private static final String DB_PASSWORD = "!@#My1Sql987";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR");
            throw new RuntimeException(e);

        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    static {
        try {

            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.driver", DB_DRIVER);
            properties.setProperty("hibernate.connection.url", DB_URL);
            properties.setProperty("hibernate.connection.username", DB_USERNAME);
            properties.setProperty("hibernate.connection.password", DB_PASSWORD);
            properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("show_sql", "true");
            properties.setProperty("CURRENT_SESSION_CONTEXT_CLASS", "thread");
            properties.setProperty("hibernate.hbm2ddl.auto", "");

            sessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

            System.out.println("DB my_db подключена!");


        } catch (HibernateException e) {
            System.out.println("DB my_db НЕ подключена!");
            e.printStackTrace();
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

}
