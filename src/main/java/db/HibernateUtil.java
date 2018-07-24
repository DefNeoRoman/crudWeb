package db;
import java.util.Properties;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static Properties prop = DbHelper.getProperties();
    private static SessionFactory sessionJavaConfigFactory;
    private static  EntityManagerFactory emf = Persistence.createEntityManagerFactory("model");
    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            props.put("hibernate.connection.driver_class",prop.getProperty("driver"));
            props.put("hibernate.connection.url",prop.get("url"));
            props.put("hibernate.connection.username", prop.get("user"));
            props.put("hibernate.connection.password", prop.get("password"));
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.hbm2ddl.auto", "none");
            props.put("hibernate.default_schema", "crudApplication");
            props.put("hibernate.show_sql", "true");
            configuration.setProperties(props);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
             return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) {
            sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        }

        return sessionJavaConfigFactory;
    }

    public static EntityManager getEm() {
        return emf.createEntityManager();
    }
    public static void close(){
        emf.close();
    }
}

