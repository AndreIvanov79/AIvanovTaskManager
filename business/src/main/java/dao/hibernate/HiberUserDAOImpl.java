package dao.hibernate;

import dao.daoImpl.UserDAO;
import entity.Task;
import entity.User;
import org.apache.log4j.Logger;
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

public class HiberUserDAOImpl implements UserDAO {
    private static final Logger LOG = Logger.getLogger(HiberUserDAOImpl.class);
    private static SessionFactory sessionFactory;

    @Override
    public void createUser(String firstName, String lastName, String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(firstName, lastName, userName);
            session.save(user);
            transaction.commit();

            LOG.info("Created User: " + user.toString());
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
               LOG.error("Transaction rolled back."+ e.getMessage());
            }
        }
    }

    @Override
    public List<User> showAllUsers() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        List results=null;

        transaction = session.beginTransaction();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        results = query.list();
        transaction.commit();
        for (Object res: results){
            LOG.info(res.toString());
        }
        return results;


/*

        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<User> criteria=builder.createQuery(User.class);
        Root<User> root=criteria.from(User.class);
        criteria.select(root);
        List<User> users=session.createQuery(criteria).getResultList();
        //session.close();
        for (User user: users){
            LOG.info(user.toString());
        }
        return users;*/
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
    public void createUserAndAssignTask(String firstName,String lastName,String userName,String taskTitle, String description){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
        transaction = session.beginTransaction();
        User user = new User(firstName, lastName, userName);
        session.save(user);
        System.out.println(user.toString());

        Task task = new Task(taskTitle, description);
        task.setUserID(user);
        System.out.println(task.toString());

        user.addTaskToList(task);
        session.saveOrUpdate(task);

        transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.error("Transaction rolled back."+e.getMessage());
            }
        }

    }

    public static int getUserIDByUserName(String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        int res=0;
        try( Session session = sessionFactory.openSession();) {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<User> criteria = builder.createQuery(User.class);
                Root<User> root = criteria.from(User.class);
                criteria.select(root).where(builder.like(root.get("userName"), userName));
                List<User> users = session.createQuery(criteria).getResultList();
                //session.close();
                res = users.get(0).getUserID();
        }catch (NullPointerException e){
            LOG.error("This user not exist. "+e.getMessage());
        }
        System.out.println(res);
        return res;
    }

    public Object getUserByUserName(String userName){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        return session.get(User.class, getUserIDByUserName(userName));
    }

    public static void main(String[] args) {
        HiberUserDAOImpl hiberUserDAO=new HiberUserDAOImpl();
       // hiberUserDAO.getUserByUserName("user11");
        hiberUserDAO.getUserByUserName("user1");

    }
}
