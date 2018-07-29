package app.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    private static Properties properties = new Properties();
    public static UserDao getConfiguredDao() {
        UserDao userDao = new UserDaoHibernateImpl();
        InputStream inputStream = UserDaoFactory.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
            String configuration = properties.getProperty("configuration");
            // Переделать с енумом
            if(configuration.equals("jdbc")){

                userDao = new UserDaoJDBCImpl();

            }else if(configuration.equals("entityManager")){

                userDao = new UserDaoEntityManagerImpl();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       return userDao;
    }
}
