package hibernateTests;

import db.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void javaConfigTest(){
        User user = new User();
        user.setName("Lisa");
        user.setEmail("Manager@ed.ru");
        user.setAge(123456);
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("Employee ID="+user.getId());
        sessionFactory.close();
    }

}
