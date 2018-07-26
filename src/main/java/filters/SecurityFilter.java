package filters;

import model.User;
import service.UserAccountService;
import service.UserAccountServiceImpl;
import wrappers.UserRoleRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SecurityFilter implements Filter {
    UserAccountService userAccountService = new UserAccountServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String servletPath = request.getServletPath();
        HttpSession session = request.getSession();
        User loginedUser = userAccountService.getLoginedUser(session);
        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String userName = loginedUser.getName();
            // Roles
            String role = loginedUser.getRole();
            // Wrap old request by a new Request with userName and Roles information.
            wrapRequest = new UserRoleRequestWrapper(userName, role, request);
        }
        // Pages must be signed in.


        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void destroy() {

    }
}
