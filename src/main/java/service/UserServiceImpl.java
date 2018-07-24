package service;

import dao.UserDao;
import dao.UserDaoEntityManagerImpl;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoEntityManagerImpl();
    }


    public List<User> getAllUsers(int offset, int limit) {
        return userDao.getAllUsers(offset, limit);
    }
    public List<User> getLastUsers(int limit) {
        return userDao.getLastUsers(limit);
    }
    public User getUserById(String userId) {
        return userDao.getUserById(Integer.parseInt(userId));
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

}
