package jm.task.core.jdbc.dao;

import org.hibernate.Session;
import jm.task.core.jdbc.model.User;

import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSession;

public class UserDaoHibernateImpl implements UserDao {

    private static final Session session = getSession();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`User` (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            System.out.println("Table \"User\" create successfully!");
            getSession().createSQLQuery(getSession().getEntityName());
            session.close();

        } catch (Exception e) {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {

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
