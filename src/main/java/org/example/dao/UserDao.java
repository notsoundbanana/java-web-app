package org.example.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

            System.out.println("Connection to Store DB succesfull!");

        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public void save() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user (email, login, password) VALUES ('email2', 'login2', 'password2')");
            statement.executeUpdate("INSERT INTO user (email, login, password) VALUES ('email3', 'login2', 'password2')");
            statement.executeUpdate("INSERT INTO user (email, login, password) VALUES ('email4', 'login2', 'password2')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}