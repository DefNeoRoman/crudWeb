package dao;

import db.HibernateUtil;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class UserDaoEntityManagerImpl implements UserDao {

    private  EntityManager em;

    public UserDaoEntityManagerImpl() {
        this.em = HibernateUtil.getEm();
    }

    @Override
    public void addUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user);
        transaction.commit();
    }

    @Override
    public void updateUser(User user) {
        User user1 = em.find(User.class, user.getId());
        em.getTransaction().begin();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setCreatedDate(user.getCreatedDate());
        user1.setAge(user.getAge());
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(int userId) {
        User user = em.find(User.class, Long.valueOf(userId));
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers(int offSet, int limit) {
        Query query = em.createQuery("from User ");
        query.setFirstResult(offSet);
        query.setMaxResults(limit);
        List<User> resultList = (List<User>)query.getResultList();
        return resultList;
    }

    @Override
    public User getUserById(int userId) {
        return em.find(User.class,userId);
    }

    @Override
    public List<User> getLastUsers(int limit) {
        Query queryTotal = em.createQuery
                ("Select count(f.id) from User f");
        int countResult = (int) (long)queryTotal.getSingleResult();
        return getAllUsers(countResult-limit,limit);
    }
}
