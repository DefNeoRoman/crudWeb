package controller;

import app.AppServiceManager;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEditController extends HttpServlet {
    private UserService service;
    public UserEditController() {
        service = AppServiceManager.getUserHibernateService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user;
        if(userId.equals("0")){
            user = new User();
        }else{
            user = service.getUserById(userId);
        }
        req.setAttribute("user",user);
        req.getRequestDispatcher("../jsp/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        User user;
        boolean create = false;
        if (userId.equals("0")) {
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
        response.sendRedirect("/user");
    }
}
