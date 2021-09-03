package dao.hibernate;

import dao.daoImpl.UserDAO;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HiberUserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(HiberUserDAOImpl.class);
    private static SessionFactory sessionFactory;

    @Override
    public void createUser(String firstName, String lastName, String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;
        User user=null;
        if(firstName==null || lastName==null || userName==null){
            LOG.error("Wrong arguments. Enter valid data.");
        }

        try(Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                user = new User(firstName, lastName, userName);
                session.save(user);
                transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
               LOG.error("Transaction rolled back."+ e.getMessage());
            }
        }
        LOG.info("Created User: " + user.toString());
    }

    @Override
    public List<User> showAllUsers() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> results=null;

        transaction = session.beginTransaction();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        results = query.list();
        transaction.commit();
        for (User res: results){
            LOG.info(res.toString());
        }
        return results;
    }

    public void updateUser(int id, String firstName ) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            user.setFirstName(firstName);
            session.update(user);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                LOG.error("Transaction rolled back."+e.getMessage());
            }
        }
    }

    public void removeUser(int id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
        transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        }catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
                LOG.error("Transaction rolled back."+e.getMessage());
            }
        }
    }

    @Override
    public User createUserAndAssignTask(String firstName,String lastName,String userName,String taskTitle, String description){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;
        User user = new User(firstName, lastName, userName);

        try(Session session = sessionFactory.openSession()){
        transaction = session.beginTransaction();
        session.save(user);

        Task task = new Task(taskTitle, description);
        task.setUserID(user);

        user.addTaskToList(task);
        session.saveOrUpdate(task);

        transaction.commit();
            LOG.info("Created User: "+user+" and Task: "+task+" assigned to User.");

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.error("Transaction rolled back."+e.getMessage());
            }
        }
        return user;
    }

    public User getUserByUserName(String userName){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = null;
        List<User> results=null;

        transaction = session.beginTransaction();
        Query query = session.createQuery("from User u where u.userName=:userName");
        query.setParameter("userName",userName);
        results = query.list();
        transaction.commit();
        return results.get(0);
    }
}
