package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try(UserDao userDao = new UserDao()){
            if(request.getParameterNames().hasMoreElements()){
                if(request.getParameter("action").equals("getUserByID")){
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    User userById = userDao.getUserById(userId);
                    request.setAttribute("userById",userById);
                    PrintWriter writer = response.getWriter();
                    writer.print(objectMapper.writeValueAsString(userById));
                }
            }else{
                List<User> allUsers = userDao.getAllUsers();
                request.setAttribute("userList",allUsers);
                request.setAttribute("userById",new User());
                request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //insert
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try(UserDao userDao = new UserDao()){
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setEmail(request.getParameter("age"));
            userDao.addUser(user);
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // update
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)  {
        try(UserDao userDao = new UserDao()){
            int userId = Integer.parseInt(request.getParameter("userId"));
            userDao.deleteUser(userId);
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
