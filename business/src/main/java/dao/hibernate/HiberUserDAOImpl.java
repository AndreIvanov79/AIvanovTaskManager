package dao.hibernate;

import dao.daoImpl.UserDAO;
import entity.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        User user = new User(firstName, lastName, userName);
        session.save(user);
        transaction.commit();
        session.close();
        LOG.info("Created User: "+user.toString());
    }

    @Override
    public List<User> showAllUsers() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = this.sessionFactory.openSession();
        //Transaction transaction = null;

        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<User> criteria=builder.createQuery(User.class);
        Root<User> root=criteria.from(User.class);
        criteria.select(root);
        List<User> users=session.createQuery(criteria).list();
        session.close();
        for (User user: users){
            LOG.info(user.toString());
        }
        return users;
    }

    public void updateUser(int id, String firstName ) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        User user =(User) session.get(User.class, id);
        user.setFirstName(firstName);
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void removeUser(int id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }


}
