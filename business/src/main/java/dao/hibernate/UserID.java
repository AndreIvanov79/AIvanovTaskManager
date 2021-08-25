package dao.hibernate;

import dao.jdbc.TaskDAOImpl;
import entity.Task;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserID {
   private static SessionFactory sessionFactory;

    public static int getUserID(String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        //Transaction transaction = null;

        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<User> criteria=builder.createQuery(User.class);
        Root<User> root=criteria.from(User.class);
        criteria.select(root).where(builder.like(root.get("userName"), userName));
        List<User> users=session.createQuery(criteria).getResultList();
        session.close();
        int res= users.get(0).getUserID();
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        TaskDAOImpl taskDAO=new TaskDAOImpl();
        taskDAO.getUser("user2");
        getUserID("user2");
        GetUser.getUser("user2");
    }
}
