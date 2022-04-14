package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection myConnection = Util.getConnection();

    public void createUsersTable() {
        try (Statement myStatement = myConnection.createStatement()) {
            myStatement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS Users(" +
                            "id INT NOT NULL AUTO_INCREMENT," +
                            "name VARCHAR(45) NOT NULL," +
                            "lastName VARCHAR (45) NOT NULL," +
                            "age INT NOT NULL," +
                            "PRIMARY KEY (id))");
            System.out.println("Table UserTest was created");
        } catch (SQLException e) {
            System.out.println("Table creating Error");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement myStatement = myConnection.createStatement()) {
            myStatement.executeUpdate("DROP TABLE IF EXISTS Users");
            System.out.println("Table UserTest was deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement myPrepStatement = myConnection.prepareStatement(
                     "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)")) {
            myPrepStatement.setNString(1, name);
            myPrepStatement.setNString(2, lastName);
            myPrepStatement.setInt(3, age);
            myPrepStatement.executeQuery();
            System.out.printf("Saved user %s %s %d\n", name, lastName, age);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement myPrepStatement = myConnection.prepareStatement(
                     "DELETE FROM Users WHERE id = (?)")) {
            myPrepStatement.setLong(1, id);
            myPrepStatement.executeQuery();
            System.out.println("User with id " + id + " has been removed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try (Statement myStatement = myConnection.createStatement();
             ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM Users")) {
            while (myResultSet.next()) {
                User myUser = new User();
                myUser.setId(myResultSet.getLong("id"));
                myUser.setName(myResultSet.getString("name"));
                myUser.setLastName(myResultSet.getString("lastName"));
                myUser.setAge(myResultSet.getByte("age"));
                userList.add(myUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement myStatement = myConnection.createStatement()) {
            myStatement.executeUpdate("TRUNCATE Users");
            System.out.println("The table Users was cleared");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

