package dao;

import db.HibernateUtil;
import model.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void addUser(User user) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        session1.save(user);
        session1.getTransaction().commit();
        session1.close();
    }

    @Override
    public void updateUser(User user) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        session1.update(user);
        session1.getTransaction().commit();
        session1.close();
    }


    @Override
    public void deleteUser(int userId) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        User o = (User) session1.get(User.class, BigInteger.valueOf(userId));
        session1.delete(o);
        session1.getTransaction().commit();
        session1.close();
    }

    @Override
    public List<User> getAllUsers(int offSet, int limit) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        Query query = session1.createQuery("from User");
        query.setFirstResult(offSet);
        query.setMaxResults(limit);
        @SuppressWarnings("unchecked")
        List<User> list = (List<User>) query.list();
        session1.close();
        return list;
    }

    @Override
    public User getUserById(int userId) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        User o = (User) session1.get(User.class, BigInteger.valueOf(userId));
        session1.close();
        return o;
    }

    @Override
    public List<User> getLastUsers(int limit) {
        Session session1 = HibernateUtil.getSessionFactory().openSession();
        session1.beginTransaction();
        SQLQuery sqlQuery = session1.createSQLQuery("SELECT COUNT(*) from users");
        BigInteger count = (BigInteger) sqlQuery.list().get(0);
        session1.close();
        return getAllUsers(count.intValue() - limit, limit);
    }
}
