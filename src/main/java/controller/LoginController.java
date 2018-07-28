package controller;


import app.AppManager;
import model.User;
import service.UserAccountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet{
    private UserAccountService userAccountService;
    public LoginController() {
        userAccountService = AppManager.getAccountService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        String password = req.getParameter("password");
        User user = userAccountService.findUserByNameAndPassword(userName, password);
        if (user == null) {
            String errorMessage = "Invalid userName or Password";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        storeLoginedUser(req.getSession(), user);
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(req.getParameter("redirectId"));
        } catch (Exception e) {
        }
        String requestUri = userAccountService.getRedirectAfterLoginUrl(redirectId);
        if (requestUri != null) {
            resp.sendRedirect(requestUri);
        } else {
            resp.sendRedirect(req.getContextPath() + "/userInfo");
        }
    }
    public  void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }
}
