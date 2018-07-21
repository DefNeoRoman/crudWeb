package db;
import java.util.Properties;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static SessionFactory sessionAnnotationFactory;

    private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
               throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
              throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost/crudApplication?serverTimezone=UTC");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "root");
            props.put("hibernate.current_session_context_class", "thread");
            configuration.setProperties(props);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
             return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }
}
