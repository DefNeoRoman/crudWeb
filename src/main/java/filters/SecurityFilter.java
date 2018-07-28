package filters;

import app.AppManager;
import model.User;
import service.UserAccountService;
import wrappers.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class SecurityFilter implements Filter {

    UserAccountService userAccountService;

    public SecurityFilter() {
        this.userAccountService = AppManager.getAccountService();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest wrapRequest = request;
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        if (loginedUser != null) {
            String userName = loginedUser.getName();
            String roles = loginedUser.getRole();
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
            boolean hasPermission = userAccountService.hasPermission(wrapRequest);
            if (hasPermission) {
                chain.doFilter(wrapRequest, response);
            } else {
                ServletContext servletContext = request.getServletContext();
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("jsp/accessDenied.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else {
            chain.doFilter(wrapRequest, response);
        }
    }

    @Override
    public void destroy() {

    }
}
