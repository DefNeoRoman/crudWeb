package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDao;
import entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            user.setAge(Integer.parseInt(request.getParameter("age")));
            userDao.addUser(user);
         } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // update
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)  {
        try(UserDao userDao = new UserDao()){
            int userId = Integer.parseInt(request.getParameter("userId"));
            userDao.deleteUser(userId);
         } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
