package service;

import dao.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public class UserService {

    private UserDaoJDBCImpl userDaoJDBCImpl;

    public UserService() {
        userDaoJDBCImpl = new UserDaoJDBCImpl();
    }

    public List<User> getAllUsers(int offset, int limit) {
        return userDaoJDBCImpl.getAllUsers(offset, limit);
    }
    public List<User> getLastUsers(int limit) {
        return userDaoJDBCImpl.getLastUsers(limit);
    }
    public User getUserById(String userId) {
        return userDaoJDBCImpl.getUserById(Integer.parseInt(userId));
    }

    public void addUser(User user) {
        userDaoJDBCImpl.addUser(user);
    }

    public void updateUser(User user) {
        userDaoJDBCImpl.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDaoJDBCImpl.deleteUser(userId);
    }

}
