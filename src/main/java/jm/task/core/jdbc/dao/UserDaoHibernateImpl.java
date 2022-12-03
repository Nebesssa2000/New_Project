package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {

    private static final Session session = getSession();
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

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Table \"User\" create successfully!");
            transaction.commit();
            session.close();

        } catch (Exception e) {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = getSession()) {
            String sql = "DROP TABLE IF EXISTS User";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Table \"User\" drop successfully!");
            transaction.commit();
            session.close();

        } catch (Exception e) {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
