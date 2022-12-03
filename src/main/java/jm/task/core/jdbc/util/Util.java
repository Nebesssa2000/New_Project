package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

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
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DB_SHOW = "true";
    private static final String DB_CURRENT_SESSION = "thread";
    private static final String DB_HBM2DDL_AUTO = "";

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

     public static SessionFactory getSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.setProperty(Environment.DRIVER, DB_DRIVER);
            properties.setProperty(Environment.URL, DB_URL);
            properties.setProperty(Environment.USER, DB_USERNAME);
            properties.setProperty(Environment.PASS, DB_PASSWORD);
            properties.setProperty(Environment.DIALECT, DB_DIALECT);
            properties.setProperty(Environment.SHOW_SQL, DB_SHOW);
            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, DB_CURRENT_SESSION);
            properties.setProperty(Environment.HBM2DDL_AUTO, DB_HBM2DDL_AUTO);

            sessionFactory = new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

            System.out.println("DB my_db подключена!");


        } catch (HibernateException e) {
            System.out.println("DB my_db НЕ подключена!");
            e.printStackTrace();
        }
         return sessionFactory;
     }

    public static Session getSession() throws HibernateException {
        return getSessionFactory().openSession();
    }

}
