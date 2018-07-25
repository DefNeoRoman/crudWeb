package filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/AuthFilter")
public class AuthFilter implements Filter{
    Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Auth filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Auth filter filtered");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
