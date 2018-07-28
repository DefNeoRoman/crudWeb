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

@WebFilter("/*")
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
        String servletPath = request.getServletPath();
        User loginedUser = getLoginedUser(request.getSession());
        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;
        if (loginedUser != null) {
            String userName = loginedUser.getName();
            String roles = loginedUser.getRole();
            wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
        }

        if (userAccountService.isSecurityPage(request)) {
           if (loginedUser == null) {

                String requestUri = request.getRequestURI();
                int redirectId = userAccountService.storeRedirectAfterLoginUrl(requestUri);
                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }
            boolean hasPermission = userAccountService.hasPermission(wrapRequest);
            if (!hasPermission) {
          RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("jsp/accessDenied.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }
    public  User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute("loginedUser");
    }

    @Override
    public void destroy() {

    }
}
