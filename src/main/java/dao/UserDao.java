package dao;

import db.InitDB;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = InitDB.getConnection();
    }

    public void addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into users(age,name,email,createdDate) values (?, ?, ?, ? )")) {
            executeUpdate(preparedStatement, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET age=?, name=?, email=?, createdDate=? WHERE id =?;")) {
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
            if (!user.isNew()) {
                preparedStatement.setLong(i++, user.getId());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=?")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(int offSet, int limit) {
        List<User> users = new ArrayList<User>();
        int i = 1;
        try (PreparedStatement statement = connection.prepareStatement("select * from users limit ?, ?")
        ) {
            statement.setInt(i++, offSet);
            statement.setInt(i++, limit);
            ResultSet rs = statement.executeQuery();
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
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?")) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
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
}
