package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
        User loginedUser = (User) session.getAttribute("loginedUser");
        if ("/register".equals(request.getServletPath())) {
            chain.doFilter(req, resp);
            return;
        }
        if (loginedUser != null) {
            String userName = loginedUser.getName();
            req.setAttribute("userName", userName);
            chain.doFilter(request, response);
        } else {
            String name = req.getParameter("name");
            if (name != null) {
                if (" ".equals(name) || name.isEmpty()) {
                    req.setAttribute("message", "no active login");
                    RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/login.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
