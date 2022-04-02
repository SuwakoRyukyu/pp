package jm.task.core.jdbc.util;

import java.sql.*;

// The reason I'm using Mariadb is that the default implementation of MySQL on my Linux distro (Arch Linux) is Mariadb, and there's no actual way to change it to MySQL as far as I can see.
public class Util {
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

    public static void close() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}