package dao;

import db.DbHelper;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = DbHelper.getConnection();
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
    public List<User> getLastUsers(int limit){
        int count = 0;
        int i = 1;
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) from users;")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          return getAllUsers(count-limit,limit);
    }
}
