package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final Session mySession = Util.getSession();
    @Override
    public void createUsersTable() {
        Transaction myTransaction = null;
        try {
            myTransaction = mySession.beginTransaction();
            mySession.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS User(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR (45) NOT NULL," +
                    "age INT NOT NULL," +
                    "PRIMARY KEY (id))");
            myTransaction.commit();
        } catch (Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction myTransaction = null;
        try {
            myTransaction = mySession.beginTransaction();
            mySession.createSQLQuery("DROP TABLE IF EXISTS User");
            myTransaction.commit();
        } catch (Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction myTransaction = null;
        try {
            myTransaction = mySession.beginTransaction();
            mySession.persist(new User(name, lastName, age));
            mySession.getTransaction().commit();
        } catch (Exception e) {
            if (myTransaction != null) {
                myTransaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction myTransaction = null;
        try {
            myTransaction = mySession.beginTransaction();
            mySession.createQuery("DELETE FROM User WHERE id = :id");
            myTransaction.commit();
        } catch (Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction myTransaction = null;
        List<User> userList = null;
        try {
            myTransaction = Util.getSession().beginTransaction();
            userList = Util.getSession().createQuery("from User").getResultList();
            myTransaction.commit();
        } catch (Exception e) {
           assert myTransaction != null;
           myTransaction.rollback();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction myTransaction = null;
        try {
            myTransaction = mySession.beginTransaction();
            mySession.createQuery("delete from User");
            myTransaction.commit();
        } catch(Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
        }
    }
}
