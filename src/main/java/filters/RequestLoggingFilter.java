package filters;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class RequestLoggingFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("RequestLoggingFilter filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("request {}",servletRequest.getLocalAddr());
        logger.info("RequestLoggingFilter filtered");
    }

    @Override
    public void destroy() {
        logger.info("RequestLoggingFilter filter destroy");
    }
}
