package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS UserTest(" +
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
        try (Connection conn = Util.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS UserTest");
            System.out.println("Table UserTest was deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO UserTest (name, lastName, age) VALUES (?, ?, ?)")) {
            ps.setNString(1, name);
            ps.setNString(2, lastName);
            ps.setInt(3, age);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM UserTest WHERE id = (?)")) {
            ps.setLong(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try (Connection conn = Util.getConnection();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery("SELECT * FROM UserTest")) {
            while (rs.next()) {
                User myUser = new User();
                {
                    myUser.setId(rs.getLong("id"));
                    myUser.setName(rs.getString("name"));
                    myUser.setLastName(rs.getString("lastName"));
                    myUser.setAge(rs.getByte("age"));
                }
                userList.add(myUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement st = conn.createStatement()) {
                st.executeUpdate("TRUNCATE UserTest");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

