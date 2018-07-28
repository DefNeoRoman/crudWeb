package service;

import model.User;

import java.util.List;

public interface UserService {

     List<User> getAllUsers(int offset, int limit);
     List<User> getLastUsers(int limit);
     User getUserById(String userId);
     void addUser(User user);
     void updateUser(User user);
     void deleteUser(int userId);
     User findUserByNameAndPassword(String userName, String password);

}
