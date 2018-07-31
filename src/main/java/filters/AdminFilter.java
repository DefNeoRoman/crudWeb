package filters;

import app.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpSession session = request1.getSession();
    //    User loginedUser = (User) session.getAttribute("loginedUser");
     //   request.setAttribute("userName", loginedUser.getName());
//      //  if (!(loginedUser.getRole().equals("ADMIN"))) {
//            request1.setAttribute("message", "succes login, but bad permissions");
//            ServletContext servletContext = request.getServletContext();
//            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/accessDenied.jsp");
//            dispatcher.forward(request, response);
//            return;
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
