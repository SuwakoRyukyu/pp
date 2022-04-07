package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    @Override
    public void createUsersTable() {
        Transaction myTransaction = null;
        try (Session mySession = Util.getSession()) {
            myTransaction = mySession.beginTransaction();
            mySession.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS Users(" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "firstName VARCHAR(45) NOT NULL," +
                    "lastName VARCHAR (45) NOT NULL," +
                    "age INT NOT NULL," +
                    "PRIMARY KEY (id))").addEntity(User.class).executeUpdate();
            myTransaction.commit();
        } catch (Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction myTransaction = null;
        try (Session mySession = Util.getSession()){
            myTransaction = mySession.beginTransaction();
            mySession.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            mySession.getTransaction().commit();
        } catch (Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction myTransaction = null;
        try (Session mySession = Util.getSession()) {
            User myUser = new User(name, lastName, age);
            myTransaction = mySession.beginTransaction();
            mySession.persist("User", myUser);
            mySession.getTransaction().commit();
        } catch (Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction myTransaction = null;
        try (Session mySession = Util.getSession()){
            myTransaction = mySession.beginTransaction();
            Query myQuery = mySession.createQuery("delete User where id = :id");
            myQuery.setParameter("id", id);
            myQuery.executeUpdate();
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
        try (Session mySession = Util.getSession()){
            myTransaction = mySession.beginTransaction();
            userList = mySession.createQuery("from User", User.class).getResultList();
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
        try (Session mySession = Util.getSession()){
            myTransaction = mySession.beginTransaction();
            mySession.createQuery("delete from User").executeUpdate();
            myTransaction.commit();
        } catch(Exception e) {
            assert myTransaction != null;
            myTransaction.rollback();
        }
    }
}
