package filters;

import app.AppManager;
import model.User;
import service.UserAccountService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    private UserAccountService userAccountService;

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
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();
        User loginedUser = (User) session.getAttribute("loginedUser");
        if (loginedUser != null && !loginedUser.isNew()) {
            String userName = loginedUser.getName();
            req.setAttribute("userName", userName);
            chain.doFilter(request, response);
        } else {
            String name = req.getParameter("name");
            if(name != null){
                if(" ".equals(name) || name.isEmpty()){
                    req.setAttribute("message","no active login");
                    RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/jsp/login.jsp");
                    dispatcher.forward(request, response);
                    return;
                }else{
                    chain.doFilter(request, response);
                }
            }else{
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
