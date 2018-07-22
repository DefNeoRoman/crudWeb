package hibernateTests;

import db.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateTest {
    private EntityManagerFactory entityManagerFactory;
    @Test
    public void javaConfigTest(){
        User user = new User();
        user.setName("Lisa");
        user.setEmail("Manager@ed.ru");
        user.setAge(123456);
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        sessionFactory.close();
    }
    @Test
    public void persistenceTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("model");
        //New op
        User user = new User();
        user.setAge(90);
        user.setEmail("fhg@er.ru");
        user.setName("sd");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user); // op is MANAGED now
        em.flush();
        em.remove(user); // op is REMOVED now
        em.persist(user); // op is MANAGED now
        user.setName("tutututuut");
        em.getTransaction().commit();
        em.close(); // op is DETACHED now
        //Merge back
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        user = em.merge(user); // op is MANAGED now
        em.refresh(user); // OP is still MANAGED and reloaded from the DB
        em.getTransaction().commit();
        em.clear(); // op is DETACHED now
    }
}
