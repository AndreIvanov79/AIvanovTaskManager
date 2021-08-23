package dao.hibernate;

import dao.daoImpl.TaskDAO;
import entity.Task;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HiberTaskDAOImpl implements TaskDAO {
    private static final Logger LOG = Logger.getLogger(HiberTaskDAOImpl.class);
    private static SessionFactory sessionFactory;

    @Override
    public void createTask(String taskTitle, String description, String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Task task = new Task(taskTitle, description, userName);
        session.save(task);
        transaction.commit();
        session.close();
        LOG.info("Created Task: "+task.toString());
    }

    @Override
    public List<Task> showUserTasks(String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = this.sessionFactory.openSession();
       // Transaction transaction = null;

        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria=builder.createQuery(Task.class);
        Root<Task> root=criteria.from(Task.class);
        criteria.select(root).where(builder.like(root.get("userName"), userName));
        List<Task> userTasks=session.createQuery(criteria).getResultList();
        session.close();
        for (Task task: userTasks){
            LOG.info("The User "+userName+" has: "+task.toString());
        }
        return userTasks;
    }

    public void updateTask(int id, String description) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = this.sessionFactory.openSession();
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
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Task task = (Task) session.get(Task.class, id);
        session.delete(task);
        transaction.commit();
        session.close();
    }


}
