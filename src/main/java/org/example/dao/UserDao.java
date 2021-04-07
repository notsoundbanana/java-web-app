package org.example.dao;

import org.example.Model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class UserDao {

    private Connection connection;

    public UserDao() {
        try {
            String url = "jdbc:mysql://localhost/users_db";
            String username = "root";
            String password = "nrimi8kr";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, username, password);


        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public void add(User user) {
        try {
            Statement statement = connection.createStatement();
            String line = String.format("INSERT INTO user (email, login, password) VALUES ('%s', '%s', '%s')", user.email, user.login, user.password);
            statement.executeUpdate(line);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<User> all_users() {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                String email = resultSet.getString(2);
                String login = resultSet.getString(3);
                String password = resultSet.getString(4);
                User user = new User(email, login, password);
                userList.add(user);
            }

            return userList;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void delete(String email) {
        try {
            Statement statement = connection.createStatement();
            String line = String.format("DELETE FROM user WHERE email='%s';", email);
            statement.executeUpdate(line);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<User> edit(String userToEdit) {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            String line = String.format("SELECT * FROM user WHERE email='%s' ", userToEdit);
            ResultSet resultSet = statement.executeQuery(line);
            String email = null;
            String login = null;
            String password = null;
            while (resultSet.next()) {
                email = resultSet.getString(2);
                login = resultSet.getString(3);
                password = resultSet.getString(4);
                User user = new User(email, login, password);
                userList.add(user);
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void submit_editing(String old_email, User user) {
        try {
            Statement statement = connection.createStatement();
//            statement.executeUpdate(String.format("SET SQL_SAFE_UPDATES = 0;\n" +
//                    "update user set login='%s' WHERE email='%s'", user.login, old_email));
//            statement.executeUpdate(String.format("SET SQL_SAFE_UPDATES = 0;\n" +
//                    "update user set login='%s' WHERE email='%s'", user.password, old_email));
//            statement.executeUpdate(String.format("SET SQL_SAFE_UPDATES = 0;\n" +
//                    "update user set login='%s' WHERE email='%s'", user.email, old_email));
            statement.executeUpdate(String.format("update user set login='%s' WHERE email='%s'", user.login, old_email));
            statement.executeUpdate(String.format("update user set password='%s' WHERE email='%s'", user.password, old_email));
            statement.executeUpdate(String.format("update user set email='%s' WHERE email='%s'", user.email, old_email));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}