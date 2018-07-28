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

@WebFilter({"/login","/task/*"})
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
        ServletContext servletContext = request.getServletContext();
        User loginedUser = (User) session.getAttribute("loginedUser");
        if (loginedUser != null && !loginedUser.isNew()) {
            String userName = loginedUser.getName();
            req.setAttribute("userName", userName);
            String roles = loginedUser.getRole();
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
            boolean hasPermission = userAccountService.hasPermission(wrapRequest);
            if (hasPermission) {
                chain.doFilter(wrapRequest, response);
            } else {
                req.setAttribute("message","succes login, but bad permissions");
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/accessDenied.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else {
            String url = ((HttpServletRequest) req).getRequestURL().toString();
            if(url.contains("task")){
                req.setAttribute("message","no active login");
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/loginOrRegister.jsp");
                dispatcher.forward(request, response);
                return;
            }
            chain.doFilter(wrapRequest, response);
        }
    }

    @Override
    public void destroy() {

    }
}
