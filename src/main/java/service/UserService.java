package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private ObjectMapper objectMapper = new ObjectMapper();
    public static final int FIRST_LAUNCH = 1;
    private UserDao userDao;
    public UserService() {
        userDao = new UserDao();
    }

    public void getOneUser(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User oneUser = userDao.getUserById(userId);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(oneUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeDefaultResponse(HttpServletRequest request, HttpServletResponse response) {
        List<User> allUsers = new ArrayList<>();
        int offset = 0;
        int limit = 10;
        allUsers = userDao.getAllUsers(offset, limit);
        request.setAttribute("userList", allUsers);
        request.setAttribute("currentPage", FIRST_LAUNCH);
        int next = FIRST_LAUNCH + 1;
        request.setAttribute("next", next);
        request.setAttribute("prev", 0);
        request.setAttribute("limit", limit);
        request.setAttribute("totalRows", allUsers.size());
        try {
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    public void getUsersByLimit(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        if (currentPage < 0) {
            currentPage = 0;
        }
        List<User> allUsers = userDao.getAllUsers(currentPage * limit, limit);
        request.setAttribute("userList", allUsers);
        request.setAttribute("currentPage", currentPage);
        int next = currentPage + 1;
        request.setAttribute("next", next);
        int prev = currentPage - 1;
        request.setAttribute("prev", prev);
        request.setAttribute("limit", limit);
        request.setAttribute("totalRows", allUsers.size());
        try {
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

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
