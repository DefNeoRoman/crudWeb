package service;

import dao.UserDao;
import dao.UserDaoFactory;
import model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UserAccountService {
    private static UserAccountService INSTANCE = new UserAccountService();
    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();
    private static int REDIRECT_ID = 0;
    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();
    private UserDao userDao;

    static {
        List<String> userPatterns = new ArrayList<String>();
        userPatterns.add("/task/user");
        mapConfig.put(ROLE_USER, userPatterns);
        List<String> adminPatterns = new ArrayList<String>();
        adminPatterns.add("/task/admin");
        adminPatterns.add("/user/*");
        mapConfig.put(ROLE_ADMIN, adminPatterns);
    }

    private UserAccountService() {
        this.userDao = UserDaoFactory.getConfiguredDao();
    }

    public User findUserByNameAndPassword(String userName, String password) {
        User u = userDao.getUserByName(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    public int storeRedirectAfterLoginUrl(String requestUri) {
        Integer id = uri_id_map.get(requestUri);
        if (id == null) {
            id = REDIRECT_ID++;
            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }
        return id;
    }

    public String getRedirectAfterLoginUrl(int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }

    public Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public  List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
    public boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        Set<String> roles = getAllAppRoles();

        for (String role : roles) {
            List<String> urlPatterns = getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public  boolean hasPermission(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        Set<String> allRoles = getAllAppRoles();

        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
    private boolean hasUrlPattern(ServletContext servletContext, String urlPattern) {

        Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();

        for (String servletName : map.keySet()) {
            ServletRegistration sr = map.get(servletName);

            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }


    public String getUrlPattern(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = null;
        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
            return urlPattern;
        }
        urlPattern = servletPath;

        boolean has = hasUrlPattern(servletContext, urlPattern);
        if (has) {
            return urlPattern;
        }
        int i = servletPath.lastIndexOf('.');
        if (i != -1) {
            String ext = servletPath.substring(i + 1);
            urlPattern = "*." + ext;
            has = hasUrlPattern(servletContext, urlPattern);

            if (has) {
                return urlPattern;
            }
        }
        return "/";
    }

    public static UserAccountService getINSTANCE() {
        return INSTANCE;
    }
}
