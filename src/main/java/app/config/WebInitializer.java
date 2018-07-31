package app.config;

import filters.AdminFilter;
import filters.SecurityFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/login"};
    }

    @Override
    protected Filter[] getServletFilters() {
     return  new Filter[]{new AdminFilter(),new SecurityFilter()};
    }
}
