package controller.crud;

import app.AppManager;
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
        service = AppManager.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = service.getUserById(userId);
        req.setAttribute("user",user);
        req.setAttribute("currentPage",req.getParameter("currentPage"));
        req.setAttribute("limit",req.getParameter("limit"));
        req.getRequestDispatcher("../jsp/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String currentPage = request.getParameter("currentPage");
        String limit = request.getParameter("limit");
        User user = service.getUserById(userId);
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        service.updateUser(user);
        response.sendRedirect("/user?action=getLimit&currentPage="+currentPage+"&limit="+limit);
    }
}
