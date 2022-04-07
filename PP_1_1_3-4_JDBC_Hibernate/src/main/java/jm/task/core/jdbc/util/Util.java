package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

// The reason I'm using Mariadb is that the default implementation of MySQL on my Linux distro (Arch Linux) is Mariadb, and there's no actual way to change it to MySQL as far as I can see.
public class Util {

    public static Session getSession() {
        Session mySession = null;
        Properties myProperties = new Properties();
        try {
            myProperties.setProperty("hibernate.connection.url","jdbc:mariadb://localhost:3306/mydb");
            myProperties.setProperty("hibernate.connection.username", "root");
            myProperties.setProperty("hibernate.connection.password", "suwako");
            myProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            myProperties.setProperty("hibernate.show_sql", "true");
            myProperties.setProperty("hibernate.format_sql", "true");
            SessionFactory myFactory = new Configuration()
                    .addProperties(myProperties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            mySession = myFactory.openSession();
        } catch (Exception e) {
            System.out.println("Error while trying to initialize Hibernate util");
            e.printStackTrace();
        }
        return mySession;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            String url = "jdbc:mariadb://localhost:3306/mydb";
            String user = "root";
            String password = "suwako";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}