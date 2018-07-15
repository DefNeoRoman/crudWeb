package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {

    public static final int FIRST_LAUNCH = 1;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterNames().hasMoreElements()) {
            if (request.getParameter("action").equals("getUserByID")) {
                getOneUser(request, response);
            }else if (request.getParameter("limit").equals("0")) {
                makeDefaultResponse(request, response);
            } else {
                getUsersByLimit(request, response);
            }
        } else {
            makeDefaultResponse(request, response);
        }
    }

    private void getUsersByLimit(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        if(currentPage<0){
            currentPage = 0;
        }
        try (UserDao userDao = new UserDao()) {
            List<User> allUsers = userDao.getAllUsers(currentPage*limit, limit);
            request.setAttribute("userList", allUsers);
            request.setAttribute("currentPage", currentPage);
            int next = currentPage + 1;
            request.setAttribute("next", next);
            int prev = currentPage - 1;
            request.setAttribute("prev", prev);
            request.setAttribute("limit", limit);
            request.setAttribute("totalRows", allUsers.size());
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
            } catch (Exception e) {

        }
    }

    private void makeDefaultResponse(HttpServletRequest request, HttpServletResponse response) {
        List<User> allUsers = new ArrayList<>();
        try (UserDao userDao = new UserDao()) {
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
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getOneUser(HttpServletRequest request, HttpServletResponse response) {
        try (UserDao userDao = new UserDao()) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            User oneUser = userDao.getUserById(userId);
            PrintWriter writer = null;
            writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(oneUser));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //insert
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("id"));

        try (UserDao userDao = new UserDao()) {

            User user =  userDao.getUserById(userId);
            if(user == null){
                user = new User();
            }
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setAge(Integer.parseInt(request.getParameter("age")));
            userDao.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // update
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try (UserDao userDao = new UserDao()) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            userDao.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
