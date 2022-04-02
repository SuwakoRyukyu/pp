package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createUsersTable() {
        try {
            DatabaseMetaData dmd = getConnection().getMetaData();
            ResultSet rs = dmd.getTables(null, null, "UserTest", null);
            if (!rs.next()) {
                getConnection().createStatement().executeUpdate(SQLUser.CREATE_TABLE.QUERY);
                System.out.println("Table UserTest was created");
            } else {
                System.out.println("Table UserTest is already exists");
            }
        } catch (SQLException e) {
            System.out.println("Table creating Error");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            DatabaseMetaData dmd = getConnection().getMetaData();
            ResultSet rs = dmd.getTables(null, null, "UserTest", null);
            if (rs.next()) {
                getConnection().createStatement().executeUpdate(SQLUser.DROP_TABLE.QUERY);
                System.out.println("Table UserTest was deleted");
            } else {
                System.out.println("Table UserTest doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(SQLUser.INSERT_TO_TABLE.QUERY);
            ps.setNString(1, name);
            ps.setNString(2, lastName);
            ps.setInt(3, age);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(SQLUser.REMOVE_FROM_TABLE.QUERY);
            ps.setLong(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(SQLUser.GET.QUERY);
            while(rs.next()) {
                userList.add(new User(
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            getConnection().prepareStatement(SQLUser.CLEAN.QUERY).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public enum SQLUser {
        CREATE_TABLE("CREATE TABLE UserTest(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR (45) NOT NULL," +
                "age INT NOT NULL," +
                "PRIMARY KEY (id))"),
        DROP_TABLE("DROP TABLE UserTest"),
        INSERT_TO_TABLE("INSERT INTO UserTest (name, lastName, age) VALUES (?, ?, ?)"),
        REMOVE_FROM_TABLE("DELETE FROM UserTest WHERE id = (?)"),
        GET("SELECT * FROM UserTest"),
        CLEAN("DELETE FROM UserTest");

        final String QUERY;
        SQLUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
