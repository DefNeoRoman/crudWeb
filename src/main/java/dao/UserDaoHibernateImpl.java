package dao;

import db.HibernateUtil;
import model.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.math.BigInteger;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public Session getSession() {
        Session session1 = HibernateUtil.getSessionJavaConfigFactory().openSession();
        session1.beginTransaction();
        return session1;
    }

    @Override
    public void addUser(User user) {
        Session session = getSession();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUserByName(String name) {
        Session session = getSession();
        User o = (User) session.get(User.class, name);
        session.close();
        return o;
    }

    @Override
    public void updateUser(User user) {
        Session session = getSession();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void deleteUser(int userId) {
        Session session = getSession();
        User o = (User) session.get(User.class, Long.valueOf(userId));
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers(int offSet, int limit) {
        Session session = getSession();
        Query query = session.createQuery("from User");
        query.setFirstResult(offSet);
        query.setMaxResults(limit);
        @SuppressWarnings("unchecked")
        List<User> list = (List<User>) query.list();
        session.close();
        return list;
    }

    @Override
    public User getUserById(int userId) {
        Session session = getSession();
        User o = (User) session.get(User.class, Long.valueOf(userId));
        session.close();
        return o;
    }

    @Override
    public List<User> getLastUsers(int limit) {
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(*) from users");
        BigInteger count = (BigInteger) sqlQuery.list().get(0);
        return getAllUsers(count.intValue() - limit, limit);
    }
}
