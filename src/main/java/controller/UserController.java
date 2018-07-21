package controller;

import app.App;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {
    public static final int FIRST_LAUNCH = 1;
    private UserService service;

    public UserController() {
        service = App.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameterNames().hasMoreElements()) {
            if (request.getParameter("limit").equals("0")) {
                makeDefaultResponse(request, response);
            } else {
                getUsersByLimit(request, response);
            }
        } else {
            makeDefaultResponse(request, response);
        }
    }

    public void getUsersByLimit(HttpServletRequest request, HttpServletResponse response) {
        int currentPage = 0;
        int limit = Integer.parseInt(request.getParameter("limit"));
        List<User> allUsers = new ArrayList<>();
        String currentPage1 = request.getParameter("currentPage");
        if (currentPage1.equals("lastPage")) {
            allUsers = service.getLastUsers(limit);

        } else {
            currentPage = Integer.parseInt(currentPage1);
            allUsers = service.getAllUsers(currentPage * limit, limit);
        }
        if (currentPage < 0) {
            currentPage = 0;
        }
        request.setAttribute("userList", allUsers);
        request.setAttribute("currentPage", currentPage);
        int next = currentPage + 1;
        request.setAttribute("next", next);
        int prev = currentPage - 1;
        request.setAttribute("prev", prev);
        request.setAttribute("limit", limit);
        request.setAttribute("totalRows", allUsers.size());
        try {
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void makeDefaultResponse(HttpServletRequest request, HttpServletResponse response) {
        List<User> allUsers;
        int offset = 0;
        int limit = 10;
        allUsers = (List<User>)service.getAllUsers(offset, limit);
        request.setAttribute("userList", allUsers);
        request.setAttribute("currentPage", FIRST_LAUNCH);
        int next = FIRST_LAUNCH + 1;
        request.setAttribute("next", next);
        request.setAttribute("prev", 0);
        request.setAttribute("limit", limit);
        try {
            request.getRequestDispatcher("jsp/user.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
