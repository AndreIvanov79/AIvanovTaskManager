package dao.hibernate;

import dao.daoImpl.TaskDAO;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class HiberTaskDAOImpl implements TaskDAO {
    private static final Logger LOG = Logger.getLogger(HiberTaskDAOImpl.class);
    private static SessionFactory sessionFactory;


    @Override
    public void createTask(String taskTitle, String description, String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;
        HiberUserDAOImpl hiberUserDAO=new HiberUserDAOImpl();
        User user=hiberUserDAO.getUserByUserName(userName);
        Task task = new Task(taskTitle, description);
        task.setUserID(user);
        user.addTaskToList(task);

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(task);
            transaction.commit();

            LOG.info("Created Task: " + task.toString() + " of User: " + userName);
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public List<Task> showUserTasks(String userName) {
        HiberUserDAOImpl hiberUserDAO=new HiberUserDAOImpl();
        User user=hiberUserDAO.getUserByUserName(userName);
        if(user==null){
            LOG.error("User with "+userName+" doesn`t exist.");
            return new ArrayList<>();
        }
        return user.getMyTasks();
    }

    public void updateTask(int id, String description) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Task task =(Task) session.get(Task.class, id);
        task.setDescription(description);
        session.update(task);
        transaction.commit();
        session.close();
    }

    public void removeTask(int id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Task task = (Task) session.get(Task.class, id);
        session.delete(task);
        transaction.commit();
        session.close();
    }
}
