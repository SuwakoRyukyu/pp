package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Butercup", "Camouflage", (byte) 20);
        userService.saveUser("Bandersnatch", "Cummerbund", (byte) 25);
        userService.saveUser("Billiardball", "Banglesnatch", (byte) 31);
        userService.saveUser("Baseballmitt", "Cumberbund", (byte) 38);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.removeUserById(1);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

