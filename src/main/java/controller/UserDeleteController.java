package controller;

import app.App;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/user/delete")
public class UserDeleteController extends HttpServlet {
    private UserService service;
    public UserDeleteController() {
        service = App.getUserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String currentPage = req.getParameter("currentPage");
        String limit = req.getParameter("limit");
        service.deleteUser(userId);
        resp.sendRedirect("/user?action=getLimit&currentPage="+currentPage+"&limit="+limit);
    }
}
