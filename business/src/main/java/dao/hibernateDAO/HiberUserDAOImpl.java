package dao.hibernateDAO;

import dao.UserDAO;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HiberUserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(HiberUserDAOImpl.class);

    @Override
    public void createUser(String firstName, String lastName, String userName) {
        Transaction transaction = null;
        User user = null;
        if (firstName == null || lastName == null || userName == null) {
            LOG.error("Wrong arguments. Enter valid data.");
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = new User(firstName, lastName, userName);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.error("Transaction rolled back." + e.getMessage());
            }
        }
        LOG.info("Created User: " + user.toString());
    }

    @Override
    public List<User> showAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        List<User> users = session.createQuery(criteria).getResultList();
        users.stream()
                .forEach(user -> LOG.info(user));
        return users;

    }

    @Override
    public User createUserAndAssignTask(String firstName, String lastName, String userName, String taskTitle, String description) {
        Transaction transaction = null;
        User user = new User(firstName, lastName, userName);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);

            Task task = new Task(taskTitle, description);
            task.setUserID(user);

            user.addTaskToList(task);
            session.saveOrUpdate(task);

            transaction.commit();
            LOG.info("Created User: " + user + " and Task: " + task + " assigned to User.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.error("Transaction rolled back." + e.getMessage());
            }
        }
        return user;
    }

    public void updateUser(int id, String firstName) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            user.setFirstName(firstName);
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.error("Transaction rolled back." + e.getMessage());
            }
        }
    }

    public void removeUser(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.error("Transaction rolled back." + e.getMessage());
            }
        }
    }

    public User getUserByUserName(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root).where(builder.like(root.get("userName"), userName));
            user = session.createQuery(criteria).uniqueResult();
        } catch (NullPointerException e) {
            LOG.error("This user not exist. " + e.getMessage());
        }
        LOG.info(user.toString());
        return user;
    }
}

