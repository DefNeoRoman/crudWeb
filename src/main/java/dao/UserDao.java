package dao;

import db.InitDB;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao implements AutoCloseable{
    private Connection connection;
    public void addUser(User user) {
        connection = InitDB.getConnection();
        try (
             PreparedStatement preparedStatement = connection
                     .prepareStatement(
                             "insert into users(age,name,email,createdDate) values (?, ?, ?, ? )")) {

            executeUpdate(preparedStatement, user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(PreparedStatement preparedStatement, User user) {
        try {

            int i = 1;
            preparedStatement.setInt(i++, user.getAge());
            preparedStatement.setString(i++, user.getName());
            preparedStatement.setString(i++, user.getEmail());
            preparedStatement.setDate(i++, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        connection = InitDB.getConnection();
        try (
             PreparedStatement preparedStatement = connection
                     .prepareStatement("delete from users where userid=?")
        ) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        connection = InitDB.getConnection();
        try (
             PreparedStatement preparedStatement = connection
                     .prepareStatement(
                             "update users set age=?,name=?,email=?,createdDate=? where userid=?")

        ) {
            executeUpdate(preparedStatement, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try (Connection connection = InitDB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from users")

        ) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setAge(rs.getInt("age"));
                user.setName((rs.getString("name")));
                user.setEmail(rs.getString("email"));
                user.setCreatedDate(rs.getTimestamp("createdDate"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        connection = InitDB.getConnection();
        try (
             PreparedStatement preparedStatement = connection.
                     prepareStatement("select * from users where userid=?")
        ) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setCreatedDate(rs.getTimestamp("createdDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
