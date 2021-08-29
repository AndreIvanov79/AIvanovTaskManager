package dao.hibernate;

import dao.daoImpl.TaskDAO;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.NoSuchParameterException;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static dao.hibernate.HiberUserDAOImpl.getUserIDByUserName;
import static entity.User.getUserByUserName;


public class HiberTaskDAOImpl implements TaskDAO {
    private static final Logger LOG = Logger.getLogger(HiberTaskDAOImpl.class);
    private static SessionFactory sessionFactory;


    @Override
    public void createTask(String taskTitle, String description, String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class,getUserIDByUserName(userName));
            Task task = new Task(taskTitle, description);
            task.setUserID(user);
            System.out.println(task.toString());

            user.addTaskToList(task);
            session.saveOrUpdate(task);
            transaction.commit();

            LOG.info("Created Task: " + task.toString() + " of User: " + userName);
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            } throw e;
        }

    }

    @Override
    public List<Task> showUserTasks(String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
/*
        User user=getUserByUserName(userName);
        //List<Task> userTasks=user.getMyTasks();

        Transaction transaction = session.beginTransaction();
        String hql = "FROM Task t where t.userID= :userID";
        Query query = session.createQuery(hql);
        query.setParameter("userID",getUserByUserName(userName).getUserID());
        List<Task> res = query.getResultList();
        transaction.commit();
        session.close();
        for (Task task:res){
            LOG.info("The User "+userName+" has: "+task.toString());
        }
        return res;*/

        User user= getUserByUserName(userName);//session.get(User.class,getUserByUserName(userName).getUserID());
        List<Task> userTasks=user.getMyTasks();
        boolean exists = session.createQuery("from User where userName=:userName")
                                .setParameter("userName",userName)
                                .uniqueResult() != null;

        if(exists){
            int userID = getUserIDByUserName(userName);

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
            Root<Task> root = criteria.from(Task.class);
            criteria.select(root).where(builder.equal(root.get("userID"),userID));
            userTasks = session.createQuery(criteria).getResultList();
            session.close();
            for (Task task : userTasks) {
                LOG.info("The User " + userName + " has: " + task.toString());
            }
            return userTasks;
        } else {
            throw new NoSuchParameterException("This User doesn`t exist. Enter the existing UserName.");
        }

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
