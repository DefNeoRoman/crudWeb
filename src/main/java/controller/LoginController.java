package controller;


import app.AppManager;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService;


    public LoginController() {
        userService = AppManager.getService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        String password = req.getParameter("password");
        User user = userService.findUserByNameAndPassword(userName, password);
        if (user == null) {
            String errorMessage = "Invalid userName or Password";
            req.setAttribute("message", errorMessage);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
        }else{
            req.getSession().setAttribute("loginedUser", user);
            req.setAttribute("userName", user.getName());
            String message = "login success";
            req.setAttribute("message", message);
            req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
        }

    }
}
