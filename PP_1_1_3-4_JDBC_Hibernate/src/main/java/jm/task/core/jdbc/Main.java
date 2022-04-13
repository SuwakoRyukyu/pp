package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Butercup", "Camouflage", (byte) 20);
        userDao.saveUser("Bandersnatch", "Cummerbund", (byte) 25);
        userDao.saveUser("Billiardball", "Banglesnatch", (byte) 31);
        userDao.saveUser("Baseballmitt", "Cumberbund", (byte) 38);
        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(1);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
