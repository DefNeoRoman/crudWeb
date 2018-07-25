package service;

import dao.UserDao;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserService INSTANCE = new UserServiceImpl();

    private UserDao userDao;

    private UserServiceImpl() {

        userDao = UserDaoFactory.getConfiguredDao();
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


    public static UserService getInstance() {

        return INSTANCE;
    }

}
