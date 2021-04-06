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

    public String edit(String email) {
        try {
            Statement statement = connection.createStatement();
            String line = String.format("SELECT * WHERE email='%s' FROM user", email);
            ResultSet resultSet = statement.executeQuery(line);
            return String.valueOf(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/user/all_users";
    }

}