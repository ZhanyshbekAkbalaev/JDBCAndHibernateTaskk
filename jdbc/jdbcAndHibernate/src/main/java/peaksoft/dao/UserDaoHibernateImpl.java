package peaksoft.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import peaksoft.model.User;
import peaksoft.util.Util;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    EntityManagerFactory entityManagerFactory = Util.getEntityManager();

    @Override
    public void createUsersTable() {
    }

    @Override
    public void dropUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("drop table User u");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(new User(name, lastName, age));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void removeUserById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String rem = "delete from User where id = :id";
        entityManager.createQuery(rem).setParameter("id", id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<User> selectUFromUserU = entityManager.createQuery("select u from User u", User.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return selectUFromUserU;
    }

    @Override
    public void cleanUsersTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from User").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}