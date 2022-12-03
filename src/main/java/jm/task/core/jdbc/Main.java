package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Jack", "Black", (byte)30);
        System.out.println("User c именем Jack добавлен в базу данных.");
        userServiceImpl.saveUser("Joe", "White", (byte)35);
        System.out.println("User c именем Joe добавлен в базу данных.");
        userServiceImpl.saveUser("Bob", "Ferry", (byte)40);
        System.out.println("User c именем Bob добавлен в базу данных.");
        userServiceImpl.saveUser("Natan", "Jones", (byte)45);
        userServiceImpl.getAllUsers();
        System.out.println("User c именем Natan добавлен в базу данных.");
        System.out.println(userServiceImpl.getAllUsers().toString());
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
