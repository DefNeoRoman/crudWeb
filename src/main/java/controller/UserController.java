package controller;

import entity.User;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterNames().hasMoreElements()) {
            if (request.getParameter("action").equals("getUserByID")) {
                service.getOneUser(request, response);
            } else if (request.getParameter("limit").equals("0")) {
                service.makeDefaultResponse(request, response);
            } else {
                service.getUsersByLimit(request, response);
            }
        } else {
            service.makeDefaultResponse(request, response);
        }
    }

    //insert
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        User user;
        boolean create = false;
        if (userId.isEmpty()) {
            user = new User();
            create = true;
        } else {
            user = service.getUserById(userId);
        }
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        if (create) {
            service.addUser(user);
        } else {
            service.updateUser(user);
        }
    }

    // update
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        service.deleteUser(userId);
    }
}
