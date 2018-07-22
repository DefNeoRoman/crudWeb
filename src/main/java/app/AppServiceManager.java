package app;

import dao.UserDaoHibernateImpl;
import dao.UserDaoJDBCImpl;
import service.UserService;

public class AppServiceManager {
    private static UserService hibernateService = new UserService(new UserDaoHibernateImpl());
    private static UserService jdbcService = new UserService(new UserDaoJDBCImpl());
    public static void main(String[] args) {
        // запуск через томкат
    }

    public static UserService getUserHibernateService(){
        return hibernateService;
    }

    public static UserService getUserJDBCService(){
        return jdbcService;
    }


}
