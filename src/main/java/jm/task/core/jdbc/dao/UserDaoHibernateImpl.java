package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {

    private static Transaction transaction;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = getSession()) {
            String sql = " CREATE TABLE IF NOT EXISTS User\n " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    " name VARCHAR(64) NOT NULL,\n" +
                    " lastName VARCHAR(64) NOT NULL,\n" +
                    " age TINYINT NOT NULL, PRIMARY KEY (id));";
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Create OK");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = getSession()) {
            String sql = "DROP TABLE IF EXISTS User";
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Drop OK");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = getSession()) {
            transaction = session.beginTransaction();
                User user = new User();
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
            session.save(user);
            System.out.println("Save OK");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = getSession()) {
            transaction = session.beginTransaction();
            User user = session.load(User.class,id);
            session.delete(user);
            System.out.println("Drop OK");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Session session = getSession()) {
            transaction = session.beginTransaction();
            userList = (List<User>) session.createQuery("from User").list();
            System.out.println("GetAll OK");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = getSession()) {
            String sql = "TRUNCATE TABLE User;";
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Drop OK");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
