package app.controller.crud;

import app.AppManager;
import app.model.User;
import app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/create")
public class UserCreateController extends HttpServlet{
    private UserService service;

    public UserCreateController() {
        this.service = AppManager.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        req.setAttribute("user",user);
        req.setAttribute("currentPage",req.getParameter("currentPage"));
        req.setAttribute("limit",req.getParameter("limit"));
        req.getRequestDispatcher("../jsp/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String currentPage = request.getParameter("currentPage");
        String limit = request.getParameter("limit");
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        try {
            service.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/user?action=getLimit&currentPage="+currentPage+"&limit="+limit);
    }
}
